/*
 * This file is generated by jOOQ.
 */
package eu.fasten.core.data.metadatadb.codegen.udt;


import eu.fasten.core.data.metadatadb.codegen.Public;
import eu.fasten.core.data.metadatadb.codegen.enums.CallType;
import eu.fasten.core.data.metadatadb.codegen.udt.records.CallSiteRecord;

import javax.annotation.processing.Generated;

import org.jooq.Schema;
import org.jooq.UDTField;
import org.jooq.impl.DSL;
import org.jooq.impl.SchemaImpl;
import org.jooq.impl.UDTImpl;


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
public class CallSite extends UDTImpl<CallSiteRecord> {

    private static final long serialVersionUID = 504667979;

    /**
     * The reference instance of <code>public.call_site</code>
     */
    public static final CallSite CALL_SITE = new CallSite();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CallSiteRecord> getRecordType() {
        return CallSiteRecord.class;
    }

    /**
     * The attribute <code>public.call_site.line</code>.
     */
    public static final UDTField<CallSiteRecord, Integer> LINE = createField(DSL.name("line"), org.jooq.impl.SQLDataType.INTEGER, CALL_SITE, "");

    /**
     * The attribute <code>public.call_site.call_type</code>.
     */
    public static final UDTField<CallSiteRecord, CallType> CALL_TYPE = createField(DSL.name("call_type"), org.jooq.impl.SQLDataType.VARCHAR.asEnumDataType(eu.fasten.core.data.metadatadb.codegen.enums.CallType.class), CALL_SITE, "");

    /**
     * The attribute <code>public.call_site.receiver_namespace_id</code>.
     */
    public static final UDTField<CallSiteRecord, Long> RECEIVER_NAMESPACE_ID = createField(DSL.name("receiver_namespace_id"), org.jooq.impl.SQLDataType.BIGINT, CALL_SITE, "");

    /**
     * No further instances allowed
     */
    private CallSite() {
        super("call_site", null, null, false);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC != null ? Public.PUBLIC : new SchemaImpl(DSL.name("public"));
    }
}