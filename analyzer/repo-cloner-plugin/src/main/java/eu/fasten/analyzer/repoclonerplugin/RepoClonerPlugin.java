/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.fasten.analyzer.repoclonerplugin;

import eu.fasten.analyzer.repoclonerplugin.utils.GitCloner;
import eu.fasten.analyzer.repoclonerplugin.utils.HgCloner;
import eu.fasten.analyzer.repoclonerplugin.utils.SvnCloner;
import eu.fasten.core.data.Constants;
import eu.fasten.core.plugins.DataWriter;
import eu.fasten.core.plugins.KafkaPlugin;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.json.JSONObject;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepoClonerPlugin extends Plugin {

    public RepoClonerPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class RepoCloner implements KafkaPlugin, DataWriter {

        private String consumerTopic = "fasten.POMAnalyzer.out";
        private Throwable pluginError = null;
        private final Logger logger = LoggerFactory.getLogger(RepoCloner.class.getName());
        private String repoPath = null;
        private String artifact = null;
        private String group = null;
        private String version = null;
        private String commitTag = null;
        private String sourcesUrl = null;
        private static String baseDir = "";
        private String outputPath = null;

        @Override
        public void setBaseDir(String baseDir) {
            RepoCloner.baseDir = baseDir;
        }

        String getRepoPath() {
            return repoPath;
        }

        @Override
        public Optional<List<String>> consumeTopic() {
            return Optional.of(Collections.singletonList(consumerTopic));
        }

        @Override
        public void setTopic(String topicName) {
            this.consumerTopic = topicName;
        }

        @Override
        public Optional<String> produce() {
            var json = new JSONObject();
            json.put("artifactId", artifact);
            json.put("groupId", group);
            json.put("version", version);
            json.put("repoPath", (repoPath != null) ? repoPath : "");
            json.put("commitTag", (commitTag != null) ? commitTag : "");
            json.put("sourcesUrl", (sourcesUrl != null) ? sourcesUrl : "");
            return Optional.of(json.toString());
        }

        @Override
        public String getOutputPath() {
            return this.outputPath;
        }

        @Override
        public void consume(String record) {
            this.pluginError = null;
            this.artifact = null;
            this.group = null;
            this.version = null;
            this.repoPath = null;
            this.commitTag = null;
            this.sourcesUrl = null;
            var json = new JSONObject(record);
            if (json.has("payload")) {
                json = json.getJSONObject("payload");
            }
            artifact = json.getString("artifactId").replaceAll("[\\n\\t ]", "");
            group = json.getString("groupId").replaceAll("[\\n\\t ]", "");
            version = json.getString("version").replaceAll("[\\n\\t ]", "");
            commitTag = json.optString("commitTag").replaceAll("[\\n\\t ]", "");
            sourcesUrl = json.optString("sourcesUrl").replaceAll("[\\n\\t ]", "");
            String product = group + Constants.mvnCoordinateSeparator + artifact
                    + Constants.mvnCoordinateSeparator + version;
            outputPath = File.separator + artifact.charAt(0) + File.separator + artifact
                    + File.separator + product.replace(Constants.mvnCoordinateSeparator, "_")
                    + ".json";
            var repoUrl = json.optString("repoUrl").replaceAll("[\\n\\t ]", "");
            if (!repoUrl.isEmpty()) {
                var gitCloner = new GitCloner(baseDir);
                var hgCloner = new HgCloner(baseDir);
                var svnCloner = new SvnCloner(baseDir);
                repoPath = cloneRepo(repoUrl, artifact, group, gitCloner, hgCloner, svnCloner);
                if (repoPath != null) {
                    pluginError = null;
                } else {
                    logger.error("Could not clone repository of " + product + " from " + repoUrl,
                            pluginError);
                    return;
                }
                if (getPluginError() == null) {
                    logger.info("Cloned repository" + " of '" + product + "'"
                            + " from " + repoUrl + " to " + repoPath);
                }
            } else {
                logger.info("Repository URL not found");
            }
        }

        public String cloneRepo(String repoUrl, String artifact, String group,
                                GitCloner gitCloner, HgCloner hgCloner, SvnCloner svnCloner) {
            if (repoUrl.startsWith("scm:git:") || repoUrl.startsWith("scm:svn:")) {
                repoUrl = repoUrl.substring(8);
            } else if (repoUrl.startsWith("scm:")) {
                repoUrl = repoUrl.substring(4);
            }
            var triedGit = false;
            if (repoUrl.startsWith("git") || repoUrl.endsWith(".git")
                    || repoUrl.contains("github.com")) {
                // Most likely Git repo, try to clone with GitCloner
                triedGit = true;
                try {
                    return gitCloner.cloneRepo(repoUrl, artifact, group);
                } catch (Exception e) {
                    pluginError = e;
                }
            }
            var triedSvn = false;
            if (repoUrl.contains("svn")) {
                // Most likely a SVN repo, try to clone with SvnCloner
                triedSvn = true;
                try {
                    return svnCloner.cloneRepo(repoUrl, artifact, group);
                } catch (Exception e) {
                    pluginError = e;
                }
            }
            // If reached here then we don't really know what type of repository it is.
            // Try all types of repo cloners that haven't been tried yet.
            try {
                return hgCloner.cloneRepo(repoUrl, artifact, group);
            } catch (Exception e) {
                pluginError = e;
            }
            if (!triedGit) {
                try {
                    repoPath = gitCloner.cloneRepo(repoUrl, artifact, group);
                } catch (Exception e) {
                    pluginError = e;
                }
            }
            if (!triedSvn) {
                try {
                    repoPath = svnCloner.cloneRepo(repoUrl, artifact, group);
                } catch (Exception e) {
                    pluginError = e;
                }
            }
            return null;
        }

        @Override
        public String name() {
            return "Repo Cloner Plugin";
        }

        @Override
        public String description() {
            return "Repo Cloner Plugin. "
                    + "Consumes a repository URL, "
                    + "clones the repo to the provided directory building directory hierarchy"
                    + "and produces the path to directory with repository.";
        }

        @Override
        public String version() {
            return "0.1.0";
        }

        @Override
        public void start() {
        }

        @Override
        public void stop() {
        }

        @Override
        public Throwable getPluginError() {
            return this.pluginError;
        }

        @Override
        public void freeResource() {
        }
    }
}
