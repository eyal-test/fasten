/*
 * This file is generated by jOOQ.
 */
package eu.fasten.core.data.metadatadb.codegen;


import eu.fasten.core.data.metadatadb.codegen.tables.BinaryModuleContents;
import eu.fasten.core.data.metadatadb.codegen.tables.BinaryModules;
import eu.fasten.core.data.metadatadb.codegen.tables.Callables;
import eu.fasten.core.data.metadatadb.codegen.tables.Dependencies;
import eu.fasten.core.data.metadatadb.codegen.tables.Edges;
import eu.fasten.core.data.metadatadb.codegen.tables.Files;
import eu.fasten.core.data.metadatadb.codegen.tables.ModuleContents;
import eu.fasten.core.data.metadatadb.codegen.tables.Modules;
import eu.fasten.core.data.metadatadb.codegen.tables.Namespaces;
import eu.fasten.core.data.metadatadb.codegen.tables.PackageVersions;
import eu.fasten.core.data.metadatadb.codegen.tables.Packages;
import eu.fasten.core.data.metadatadb.codegen.tables.VirtualImplementations;

import javax.annotation.processing.Generated;


/**
 * Convenience access to all tables in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.binary_module_contents</code>.
     */
    public static final BinaryModuleContents BINARY_MODULE_CONTENTS = BinaryModuleContents.BINARY_MODULE_CONTENTS;

    /**
     * The table <code>public.binary_modules</code>.
     */
    public static final BinaryModules BINARY_MODULES = BinaryModules.BINARY_MODULES;

    /**
     * The table <code>public.callables</code>.
     */
    public static final Callables CALLABLES = Callables.CALLABLES;

    /**
     * The table <code>public.dependencies</code>.
     */
    public static final Dependencies DEPENDENCIES = Dependencies.DEPENDENCIES;

    /**
     * The table <code>public.edges</code>.
     */
    public static final Edges EDGES = Edges.EDGES;

    /**
     * The table <code>public.files</code>.
     */
    public static final Files FILES = Files.FILES;

    /**
     * The table <code>public.module_contents</code>.
     */
    public static final ModuleContents MODULE_CONTENTS = ModuleContents.MODULE_CONTENTS;

    /**
     * The table <code>public.modules</code>.
     */
    public static final Modules MODULES = Modules.MODULES;

    /**
     * The table <code>public.namespaces</code>.
     */
    public static final Namespaces NAMESPACES = Namespaces.NAMESPACES;

    /**
     * The table <code>public.package_versions</code>.
     */
    public static final PackageVersions PACKAGE_VERSIONS = PackageVersions.PACKAGE_VERSIONS;

    /**
     * The table <code>public.packages</code>.
     */
    public static final Packages PACKAGES = Packages.PACKAGES;

    /**
     * The table <code>public.virtual_implementations</code>.
     */
    public static final VirtualImplementations VIRTUAL_IMPLEMENTATIONS = VirtualImplementations.VIRTUAL_IMPLEMENTATIONS;
}
