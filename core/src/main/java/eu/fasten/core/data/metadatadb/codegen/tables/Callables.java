/*
 * This file is generated by jOOQ.
 */
package eu.fasten.core.data.metadatadb.codegen.tables;


import eu.fasten.core.data.metadatadb.codegen.Indexes;
import eu.fasten.core.data.metadatadb.codegen.Keys;
import eu.fasten.core.data.metadatadb.codegen.Public;
import eu.fasten.core.data.metadatadb.codegen.tables.records.CallablesRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.JSONB;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Callables extends TableImpl<CallablesRecord> {

    private static final long serialVersionUID = 1945056612;

    /**
     * The reference instance of <code>public.callables</code>
     */
    public static final Callables CALLABLES = new Callables();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CallablesRecord> getRecordType() {
        return CallablesRecord.class;
    }

    /**
     * The column <code>public.callables.id</code>.
     */
    public final TableField<CallablesRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('callables_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.callables.module_id</code>.
     */
    public final TableField<CallablesRecord, Long> MODULE_ID = createField(DSL.name("module_id"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.callables.fasten_uri</code>.
     */
    public final TableField<CallablesRecord, String> FASTEN_URI = createField(DSL.name("fasten_uri"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.callables.is_internal_call</code>.
     */
    public final TableField<CallablesRecord, Boolean> IS_INTERNAL_CALL = createField(DSL.name("is_internal_call"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>public.callables.created_at</code>.
     */
    public final TableField<CallablesRecord, Timestamp> CREATED_AT = createField(DSL.name("created_at"), org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>public.callables.metadata</code>.
     */
    public final TableField<CallablesRecord, JSONB> METADATA = createField(DSL.name("metadata"), org.jooq.impl.SQLDataType.JSONB, this, "");

    /**
     * Create a <code>public.callables</code> table reference
     */
    public Callables() {
        this(DSL.name("callables"), null);
    }

    /**
     * Create an aliased <code>public.callables</code> table reference
     */
    public Callables(String alias) {
        this(DSL.name(alias), CALLABLES);
    }

    /**
     * Create an aliased <code>public.callables</code> table reference
     */
    public Callables(Name alias) {
        this(alias, CALLABLES);
    }

    private Callables(Name alias, Table<CallablesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Callables(Name alias, Table<CallablesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Callables(Table<O> child, ForeignKey<O, CallablesRecord> key) {
        super(child, key, CALLABLES);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CALLABLES_COMPOUND_INDEX, Indexes.CALLABLES_PKEY);
    }

    @Override
    public Identity<CallablesRecord, Long> getIdentity() {
        return Keys.IDENTITY_CALLABLES;
    }

    @Override
    public UniqueKey<CallablesRecord> getPrimaryKey() {
        return Keys.CALLABLES_PKEY;
    }

    @Override
    public List<UniqueKey<CallablesRecord>> getKeys() {
        return Arrays.<UniqueKey<CallablesRecord>>asList(Keys.CALLABLES_PKEY);
    }

    @Override
    public List<ForeignKey<CallablesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CallablesRecord, ?>>asList(Keys.CALLABLES__CALLABLES_MODULE_ID_FKEY);
    }

    public Modules modules() {
        return new Modules(this, Keys.CALLABLES__CALLABLES_MODULE_ID_FKEY);
    }

    @Override
    public Callables as(String alias) {
        return new Callables(DSL.name(alias), this);
    }

    @Override
    public Callables as(Name alias) {
        return new Callables(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Callables rename(String name) {
        return new Callables(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Callables rename(Name name) {
        return new Callables(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, Long, String, Boolean, Timestamp, JSONB> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
