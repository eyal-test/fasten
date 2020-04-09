/*
 * This file is generated by jOOQ.
 */
package eu.fasten.core.data.metadatadb.codegen;


import eu.fasten.core.data.metadatadb.codegen.tables.Callables;
import eu.fasten.core.data.metadatadb.codegen.tables.Dependencies;
import eu.fasten.core.data.metadatadb.codegen.tables.Edges;
import eu.fasten.core.data.metadatadb.codegen.tables.Files;
import eu.fasten.core.data.metadatadb.codegen.tables.Modules;
import eu.fasten.core.data.metadatadb.codegen.tables.PackageVersions;
import eu.fasten.core.data.metadatadb.codegen.tables.Packages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = -867305040;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.callables</code>.
     */
    public final Callables CALLABLES = eu.fasten.core.data.metadatadb.codegen.tables.Callables.CALLABLES;

    /**
     * The table <code>public.dependencies</code>.
     */
    public final Dependencies DEPENDENCIES = eu.fasten.core.data.metadatadb.codegen.tables.Dependencies.DEPENDENCIES;

    /**
     * The table <code>public.edges</code>.
     */
    public final Edges EDGES = eu.fasten.core.data.metadatadb.codegen.tables.Edges.EDGES;

    /**
     * The table <code>public.files</code>.
     */
    public final Files FILES = eu.fasten.core.data.metadatadb.codegen.tables.Files.FILES;

    /**
     * The table <code>public.modules</code>.
     */
    public final Modules MODULES = eu.fasten.core.data.metadatadb.codegen.tables.Modules.MODULES;

    /**
     * The table <code>public.package_versions</code>.
     */
    public final PackageVersions PACKAGE_VERSIONS = eu.fasten.core.data.metadatadb.codegen.tables.PackageVersions.PACKAGE_VERSIONS;

    /**
     * The table <code>public.packages</code>.
     */
    public final Packages PACKAGES = eu.fasten.core.data.metadatadb.codegen.tables.Packages.PACKAGES;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.CALLABLES_ID_SEQ,
            Sequences.FILES_ID_SEQ,
            Sequences.MODULES_ID_SEQ,
            Sequences.PACKAGE_VERSIONS_ID_SEQ,
            Sequences.PACKAGES_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Callables.CALLABLES,
            Dependencies.DEPENDENCIES,
            Edges.EDGES,
            Files.FILES,
            Modules.MODULES,
            PackageVersions.PACKAGE_VERSIONS,
            Packages.PACKAGES);
    }
}
