/*
 * This file is generated by jOOQ.
 */
package eu.fasten.core.data.metadatadb.codegen.tables;


import eu.fasten.core.data.metadatadb.codegen.Keys;
import eu.fasten.core.data.metadatadb.codegen.Public;
import eu.fasten.core.data.metadatadb.codegen.tables.records.IngestedArtifactsRecord;

import java.sql.Timestamp;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.16.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class IngestedArtifacts extends TableImpl<IngestedArtifactsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.ingested_artifacts</code>
     */
    public static final IngestedArtifacts INGESTED_ARTIFACTS = new IngestedArtifacts();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<IngestedArtifactsRecord> getRecordType() {
        return IngestedArtifactsRecord.class;
    }

    /**
     * The column <code>public.ingested_artifacts.key</code>.
     */
    public final TableField<IngestedArtifactsRecord, String> KEY = createField(DSL.name("key"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.ingested_artifacts.plugin_version</code>.
     */
    public final TableField<IngestedArtifactsRecord, String> PLUGIN_VERSION = createField(DSL.name("plugin_version"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.ingested_artifacts.timestamp</code>.
     */
    public final TableField<IngestedArtifactsRecord, Timestamp> TIMESTAMP = createField(DSL.name("timestamp"), SQLDataType.TIMESTAMP(6), this, "");

    private IngestedArtifacts(Name alias, Table<IngestedArtifactsRecord> aliased) {
        this(alias, aliased, null);
    }

    private IngestedArtifacts(Name alias, Table<IngestedArtifactsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.ingested_artifacts</code> table reference
     */
    public IngestedArtifacts(String alias) {
        this(DSL.name(alias), INGESTED_ARTIFACTS);
    }

    /**
     * Create an aliased <code>public.ingested_artifacts</code> table reference
     */
    public IngestedArtifacts(Name alias) {
        this(alias, INGESTED_ARTIFACTS);
    }

    /**
     * Create a <code>public.ingested_artifacts</code> table reference
     */
    public IngestedArtifacts() {
        this(DSL.name("ingested_artifacts"), null);
    }

    public <O extends Record> IngestedArtifacts(Table<O> child, ForeignKey<O, IngestedArtifactsRecord> key) {
        super(child, key, INGESTED_ARTIFACTS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<IngestedArtifactsRecord> getPrimaryKey() {
        return Keys.INGESTED_ARTIFACTS_PKEY;
    }

    @Override
    public IngestedArtifacts as(String alias) {
        return new IngestedArtifacts(DSL.name(alias), this);
    }

    @Override
    public IngestedArtifacts as(Name alias) {
        return new IngestedArtifacts(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public IngestedArtifacts rename(String name) {
        return new IngestedArtifacts(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public IngestedArtifacts rename(Name name) {
        return new IngestedArtifacts(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, Timestamp> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
