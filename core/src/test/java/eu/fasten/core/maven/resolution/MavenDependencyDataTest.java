/*
 * Copyright 2022 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.fasten.core.maven.resolution;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.fasten.core.maven.data.Pom;
import eu.fasten.core.maven.data.VersionConstraint;

public class MavenDependencyDataTest {

    private static final long SOME_TIME = 123L;
    private MavenDependencyData sut;

    @BeforeEach
    public void setup() {
        sut = new MavenDependencyData();
    }

    @Test
    public void returnsNullIfEmpty() {
        var actual = find(SOME_TIME, "a:b", "1");
        assertNull(actual);
    }

    @Test
    public void returnsNullIfNoMatch() {
        addPom("b", "c", "1", SOME_TIME);
        var actual = find(SOME_TIME, "a:b", "1");
        assertNull(actual);
    }

    @Test
    public void returnsNullIfGroupMismatch() {
        addPom("a", "b", "1", SOME_TIME);
        var actual = find(SOME_TIME, "x:b", "1");
        assertNull(actual);
    }

    @Test
    public void returnsNullIfArtifactMismatch() {
        addPom("a", "b", "1", SOME_TIME);
        var actual = find(SOME_TIME, "a:x", "1");
        assertNull(actual);
    }

    @Test
    public void returnsNullIfVersionMismatch() {
        addPom("a", "b", "1", SOME_TIME);
        var actual = find(SOME_TIME, "a:b", "2");
        assertNull(actual);
    }

    @Test
    public void findsExactMatch() {
        var expected = addPom("a", "b", "1", SOME_TIME);
        var actual = find(SOME_TIME, "a:b", "1");
        assertEquals(expected, actual);
    }

    @Test
    public void findsExactMatchAmongMultipleVersions() {
        var expected = addPom("a", "b", "1", SOME_TIME);
        addPom("a", "b", "2", SOME_TIME);
        var actual = find(SOME_TIME, "a:b", "1");
        assertEquals(expected, actual);
    }

    @Test
    public void returnsNullIfExactMatchIsTooNew() {
        addPom("a", "b", "1", SOME_TIME + 1);
        var actual = find(SOME_TIME, "a:b", "1");
        assertNull(actual);
    }

    @Test
    public void canReplaceRegisteredPom() {
        var a = addPom("a", "b", "1", SOME_TIME);
        var b = addPom("a", "b", "1", SOME_TIME + 1);
        var c = sut.find("a:b", Set.of(new VersionConstraint("1")), SOME_TIME + 1);
        assertNotSame(a, c);
        assertSame(b, c);
    }

    @Test
    public void returnNullIfNoMatch_hardConstraint() {
        addPom("a", "b", "1.1", SOME_TIME);
        addPom("a", "b", "1.2", SOME_TIME);
        addPom("a", "b", "1.3", SOME_TIME);
        var actual = find(SOME_TIME, "a:b", "[1]");
        assertNull(actual);
    }

    @Test
    public void returnNullIfNoMatch_versionRange() {
        addPom("a", "b", "1.1", SOME_TIME);
        addPom("a", "b", "1.2", SOME_TIME);
        addPom("a", "b", "1.3", SOME_TIME);
        var actual = find(SOME_TIME, "a:b", "[1,1.1)");
        assertNull(actual);
    }

    @Test
    public void findsHighestMatch() {
        addPom("a", "b", "1.1", SOME_TIME);
        var expected = addPom("a", "b", "1.2", SOME_TIME);
        addPom("a", "b", "1.3", SOME_TIME);
        var actual = find(SOME_TIME, "a:b", "[1.1,1.3)");
        assertEquals(expected, actual);
    }

    @Test
    public void findsHighestMatchWithUnorderedConstraints() {
        addPom("a", "b", "1.1", SOME_TIME);
        addPom("a", "b", "1.2", SOME_TIME);
        addPom("a", "b", "1.3", SOME_TIME);
        addPom("a", "b", "1.4", SOME_TIME);
        var expected = addPom("a", "b", "1.5", SOME_TIME);
        addPom("a", "b", "1.6", SOME_TIME);
        var actual = find(SOME_TIME, "a:b", "[1.2,1.3]", "[1.4,1.5]", "[0,1.2]");
        assertEquals(expected, actual);
    }

    @Test
    public void findsNewestHighestMatch() {
        addPom("a", "b", "1.1", SOME_TIME - 1);
        var expected = addPom("a", "b", "1.2", SOME_TIME);
        addPom("a", "b", "1.3", SOME_TIME + 1);
        var actual = find(SOME_TIME, "a:b", "[1,2]");
        assertEquals(expected, actual);
    }

    private Pom find(long resolveAt, String ga, String... specs) {
        var vcs = Arrays.stream(specs) //
                .map(VersionConstraint::new) //
                .collect(Collectors.toSet());
        return sut.find(ga, vcs, resolveAt);
    }

    private Pom addPom(String g, String a, String v, long releaseDate) {
        var pom = new Pom();
        pom.groupId = g;
        pom.artifactId = a;
        pom.version = v;
        pom.releaseDate = releaseDate;

        sut.add(pom);
        return pom;
    }
}