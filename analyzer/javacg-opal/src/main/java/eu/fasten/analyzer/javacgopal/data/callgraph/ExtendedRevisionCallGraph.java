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

package eu.fasten.analyzer.javacgopal.data.callgraph;

import eu.fasten.analyzer.javacgopal.data.MavenCoordinate;
import eu.fasten.core.data.FastenURI;
import eu.fasten.core.data.RevisionCallGraph;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtendedRevisionCallGraph extends RevisionCallGraph {

    private static Logger logger = LoggerFactory.getLogger(ExtendedRevisionCallGraph.class);

    private final Map<FastenURI, Type> classHierarchy;
    private final Graph graph;
    private final String cgGenerator;

    public String getCgGenerator() { return cgGenerator; }

    public Map<FastenURI, Type> getClassHierarchy() {
        return classHierarchy;
    }

    public Graph getGraph() {
        return graph;
    }

    public static class Graph {

        private final List<int[]> resolvedCalls;
        private final Map<Pair<Integer, FastenURI>, Map<String, Integer>> unresolvedCalls;

        public Graph(final List<int[]> resolvedCalls, final Map<Pair<Integer, FastenURI>, Map<String, Integer>> unresolvedCalls) {
            this.resolvedCalls = resolvedCalls;
            this.unresolvedCalls = unresolvedCalls;
        }

        public Graph(final JSONObject graph) {

            final var resolvedCalls = graph.getJSONArray("resolvedCalls");
            this.resolvedCalls = new ArrayList<>();

            final int numberOfArcs = resolvedCalls.length();
            for (int i = 0; i < numberOfArcs; i++) {
                final var pair = resolvedCalls.getJSONArray(i);
                this.resolvedCalls.add(new int[]{(Integer) pair.get(0), (Integer) pair.get(1)});
            }

            final var unresolvedCalls = graph.getJSONArray("unresolvedCalls");
            this.unresolvedCalls = new HashMap<>();

            final int numberOfUnresolvedArcs = unresolvedCalls.length();
            for (int i = 0; i < numberOfUnresolvedArcs; i++) {
                final var call = unresolvedCalls.getJSONArray(i);
                final var callTypeJson = call.getJSONObject(2);
                final Map<String, Integer> callType = new HashMap<>();
                for (String type : callTypeJson.keySet()) {
                    final var number = (Integer) callTypeJson.get(type);
                    callType.put(type, number);
                }
                this.unresolvedCalls.put(new MutablePair<>(Integer.parseInt(call.getString(0)),
                    FastenURI.create(call.getString(1))), callType);
            }
        }

        public JSONObject toJSON(final Graph graph){

            final var result = new JSONObject();
            final var resolvedCallsJSON = new JSONArray();
            for (final var entry : graph.resolvedCalls) {
                resolvedCallsJSON.put(entry);
            }

            final var unresolvedCallsJSON = new JSONArray();
            for (final var entry : graph.unresolvedCalls.entrySet()) {
                final var call = new JSONArray();
                call.put(entry.getKey().getKey().toString());
                call.put(entry.getKey().getValue().toString());
                call.put(new JSONObject(entry.getValue()));
                unresolvedCallsJSON.put(call);
            }

            result.put("resolvedCalls", resolvedCallsJSON);
            result.put("unresolvedCalls", unresolvedCallsJSON);
            return result;
        }

        public JSONObject toJSON(){
            return toJSON(this);
        }

        public List<int[]> getResolvedCalls() {
            return resolvedCalls;
        }

        public Map<Pair<Integer, FastenURI>, Map<String, Integer>> getUnresolvedCalls() {
            return unresolvedCalls;
        }

        public int size() {
            return resolvedCalls.size() + unresolvedCalls.size();
        }

    }

    /**
     * Type can be a class or interface that inherits (implements) from others or implements methods.
     */
    public static class Type {
        //The source file name of this type.
        private final String sourceFileName;
        //Methods that this type implements
        private final Map<Integer, FastenURI> methods;
        //Classes that this type inherits from in the order of instantiation.
        private final LinkedList<FastenURI> superClasses;
        //Interfaces that this type or its super classes implement.
        private final List<FastenURI> superInterfaces;

        public Type(final String sourceFile, final Map<Integer, FastenURI> methods, final LinkedList<FastenURI> superClasses,
                    final List<FastenURI> superInterfaces) {
            this.sourceFileName = sourceFile;
            this.methods = methods;
            this.superClasses = superClasses;
            this.superInterfaces = superInterfaces;
        }

        public Type(final JSONObject json){

            this.sourceFileName = json.getString("sourceFile");

            final var methodsJson = json.getJSONObject("methods");
            this.methods = new HashMap<>();
            for (final String methodKey : methodsJson.keySet()) {
                this.methods.put(Integer.parseInt(methodKey),
                    FastenURI.create(methodsJson.getString(methodKey)));
            }

            final var superClassesJSON = json.getJSONArray("superClasses");
            this.superClasses = new LinkedList<>();
            final int numberOfSuperClasses = superClassesJSON.length();
            for (int i = 0; i < numberOfSuperClasses; i++) {
                this.superClasses.add(FastenURI.create(superClassesJSON.getString(i)));
            }

            final var superInterfacesJSON = json.getJSONArray("superInterfaces");
            this.superInterfaces = new ArrayList<>();
            final int numberOfsuperInterfaces = superInterfacesJSON.length();
            for (int i = 0; i < numberOfsuperInterfaces; i++) {
                this.superInterfaces.add(FastenURI.create(superInterfacesJSON.getString(i)));
            }
        }


        public JSONObject toJSON(final Type type){

            final var result = new JSONObject();

            result.put("methods", toMapOfString(type.methods));
            result.put("superClasses", toListOfString(type.superClasses));
            result.put("superInterfaces", toListOfString(type.superInterfaces));
            result.put("sourceFile", type.sourceFileName);

            return result;
        }

        public JSONObject toJSON(){
            return toJSON(this);
        }

        public String getSourceFileName() {
            return sourceFileName;
        }

        public Map<Integer, FastenURI> getMethods() {
            return this.methods;
        }

        public LinkedList<FastenURI> getSuperClasses() {
            return superClasses;
        }

        public List<FastenURI> getSuperInterfaces() {
            return superInterfaces;
        }

    }

    public ExtendedRevisionCallGraph(final String forge, final String product, final String version,
                                     final long timestamp, final List<List<Dependency>> depset,
                                     final Graph graph, final Map<FastenURI, Type> classHierarchy, final String cgGenerator) {

        super(forge, product, version, timestamp, depset, new ArrayList<>());
        this.classHierarchy = classHierarchy;
        this.graph = graph;
        this.cgGenerator = cgGenerator;
    }

    public ExtendedRevisionCallGraph(final JSONObject json) throws JSONException,
        URISyntaxException, IOException {
        super(json.getString("forge"),
            json.getString("product"),
            json.getString("version"),
            getTimeStamp(json),
            Dependency.depset(json.getJSONArray("depset")),
            new ArrayList<>());

        this.cgGenerator = json.getString("generator");
        this.graph = new Graph(json.getJSONObject("graph"));
        this.classHierarchy = classHierarchy(json.getJSONObject("cha"));

    }

    private Map<FastenURI, Type> classHierarchy(final JSONObject cha) {

        final Map<FastenURI, Type> result = new HashMap<>();

        for (final String key : cha.keySet()) {
            result.put(FastenURI.create(key), new Type(cha.getJSONObject(key)));
        }
        return result;
    }

    private static long getTimeStamp(JSONObject json) {
        try {
            return json.getLong("timestamp");
        } catch (final JSONException exception) {
            logger.warn("No timestamp provided: assuming -1");
            return -1;
        }
    }

    public static ExtendedRevisionCallGraph createWithOPAL(final String forge, final MavenCoordinate coordinate,
                                                           final long timestamp) throws FileNotFoundException {

        logger.info("Generating call graph using Opal ...");
        final var partialCallGraph = new PartialCallGraph(
            MavenCoordinate.MavenResolver.downloadJar(coordinate.getCoordinate()).orElseThrow(RuntimeException::new)
        );
        logger.info("Call graph generation is done, creating extended revision call graph ...");
        return new ExtendedRevisionCallGraph(forge,
            coordinate.getProduct(),
            coordinate.getVersionConstraint(),
            timestamp,
            MavenCoordinate.MavenResolver.resolveDependencies(coordinate.getCoordinate()),
            new Graph(partialCallGraph.getResolvedCalls(), partialCallGraph.getUnresolvedCalls()),
            partialCallGraph.getClassHierarchy(),
            partialCallGraph.getGENERATOR());
    }

    public static ExtendedRevisionCallGraph createWithOPAL(final String forge, final MavenCoordinate coordinate,
                                                           final long timestamp, final PartialCallGraph partialCallGraph) {

        final var cha = partialCallGraph.getClassHierarchy();
        return new ExtendedRevisionCallGraph(forge,
            coordinate.getProduct(),
            coordinate.getVersionConstraint(),
            timestamp,
            MavenCoordinate.MavenResolver.resolveDependencies(coordinate.getCoordinate()),
            new Graph(partialCallGraph.getResolvedCalls(), partialCallGraph.getUnresolvedCalls()),
            cha,
            partialCallGraph.getGENERATOR());
    }

    /**
     * Produces the JSON representation of this {@link ExtendedRevisionCallGraph}.
     *
     * @return the JSON representation.
     */
    @Override
    public JSONObject toJSON() {

        final var result = new JSONObject();
        result.put("forge", forge);
        result.put("product", product);
        result.put("version", version);
        result.put("generator", cgGenerator);
        if (timestamp >= 0) result.put("timestamp", timestamp);
        result.put("cha", toJSON(classHierarchy));
        result.put("depset", Dependency.toJSON(depset));
        result.put("graph", graph.toJSON());

        return result;
    }

    public JSONObject toJSON(final Map<FastenURI,ExtendedRevisionCallGraph.Type> cha){

        final var result = new JSONObject();

        for (final var entry : cha.entrySet()) {
            result.put(entry.getKey().toString(), entry.getValue().toJSON());
        }

        return result;

    }


    public static Map<Integer, String> toMapOfString(final Map<Integer, FastenURI> map) {
        final Map<Integer, String> methods = new HashMap<>();
        for (final var entry : map.entrySet()) {

            methods.put(entry.getKey(), entry.getValue().toString());
        }
        return methods;
    }

    public static List<String> toListOfString(final List<FastenURI> list) {
        final List<String> result = new ArrayList<>();
        for (final var fastenURI : list) {
            result.add(fastenURI.toString());
        }
        return result;
    }

    /**
     * Note that this is a temporary method for finding a Maven coordinate that generates an empty
     * call graph. Later on, this method might be helpful for not sending an empty call graph.
     *
     * @return boolean
     */
    public boolean isCallGraphEmpty() {
        return this.graph.resolvedCalls.isEmpty() && this.graph.unresolvedCalls.isEmpty();
    }

    public void sortResolvedCalls() {
        List<int[]> sortedList = new ArrayList<>(this.graph.resolvedCalls);
        Collections.sort(sortedList, (o1, o2) -> (Integer.toString(o1[0]) + o1[1]).compareTo(o2[0] + Integer.toString(o2[1])));
        this.graph.resolvedCalls.clear();
        this.graph.resolvedCalls.addAll(sortedList);
    }


}
