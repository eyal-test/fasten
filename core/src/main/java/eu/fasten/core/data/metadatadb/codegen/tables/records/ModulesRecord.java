/*
 * This file is generated by jOOQ.
 */
package eu.fasten.core.data.metadatadb.codegen.tables.records;


import eu.fasten.core.data.metadatadb.codegen.enums.Access;
import eu.fasten.core.data.metadatadb.codegen.tables.Modules;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.JSONB;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;


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
public class ModulesRecord extends UpdatableRecordImpl<ModulesRecord> implements Record9<Long, Long, Long, Boolean, Access, Long[], Long[], JSONB, JSONB> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.modules.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.modules.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.modules.package_version_id</code>.
     */
    public void setPackageVersionId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.modules.package_version_id</code>.
     */
    public Long getPackageVersionId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.modules.module_name_id</code>.
     */
    public void setModuleNameId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.modules.module_name_id</code>.
     */
    public Long getModuleNameId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.modules.final</code>.
     */
    public void setFinal(Boolean value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.modules.final</code>.
     */
    public Boolean getFinal() {
        return (Boolean) get(3);
    }

    /**
     * Setter for <code>public.modules.access</code>.
     */
    public void setAccess(Access value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.modules.access</code>.
     */
    public Access getAccess() {
        return (Access) get(4);
    }

    /**
     * Setter for <code>public.modules.super_classes</code>.
     */
    public void setSuperClasses(Long[] value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.modules.super_classes</code>.
     */
    public Long[] getSuperClasses() {
        return (Long[]) get(5);
    }

    /**
     * Setter for <code>public.modules.super_interfaces</code>.
     */
    public void setSuperInterfaces(Long[] value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.modules.super_interfaces</code>.
     */
    public Long[] getSuperInterfaces() {
        return (Long[]) get(6);
    }

    /**
     * Setter for <code>public.modules.metadata</code>.
     */
    public void setMetadata(JSONB value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.modules.metadata</code>.
     */
    public JSONB getMetadata() {
        return (JSONB) get(7);
    }

    /**
     * Setter for <code>public.modules.annotations</code>.
     */
    public void setAnnotations(JSONB value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.modules.annotations</code>.
     */
    public JSONB getAnnotations() {
        return (JSONB) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row9<Long, Long, Long, Boolean, Access, Long[], Long[], JSONB, JSONB> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    public Row9<Long, Long, Long, Boolean, Access, Long[], Long[], JSONB, JSONB> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Modules.MODULES.ID;
    }

    @Override
    public Field<Long> field2() {
        return Modules.MODULES.PACKAGE_VERSION_ID;
    }

    @Override
    public Field<Long> field3() {
        return Modules.MODULES.MODULE_NAME_ID;
    }

    @Override
    public Field<Boolean> field4() {
        return Modules.MODULES.FINAL;
    }

    @Override
    public Field<Access> field5() {
        return Modules.MODULES.ACCESS;
    }

    @Override
    public Field<Long[]> field6() {
        return Modules.MODULES.SUPER_CLASSES;
    }

    @Override
    public Field<Long[]> field7() {
        return Modules.MODULES.SUPER_INTERFACES;
    }

    @Override
    public Field<JSONB> field8() {
        return Modules.MODULES.METADATA;
    }

    @Override
    public Field<JSONB> field9() {
        return Modules.MODULES.ANNOTATIONS;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Long component2() {
        return getPackageVersionId();
    }

    @Override
    public Long component3() {
        return getModuleNameId();
    }

    @Override
    public Boolean component4() {
        return getFinal();
    }

    @Override
    public Access component5() {
        return getAccess();
    }

    @Override
    public Long[] component6() {
        return getSuperClasses();
    }

    @Override
    public Long[] component7() {
        return getSuperInterfaces();
    }

    @Override
    public JSONB component8() {
        return getMetadata();
    }

    @Override
    public JSONB component9() {
        return getAnnotations();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Long value2() {
        return getPackageVersionId();
    }

    @Override
    public Long value3() {
        return getModuleNameId();
    }

    @Override
    public Boolean value4() {
        return getFinal();
    }

    @Override
    public Access value5() {
        return getAccess();
    }

    @Override
    public Long[] value6() {
        return getSuperClasses();
    }

    @Override
    public Long[] value7() {
        return getSuperInterfaces();
    }

    @Override
    public JSONB value8() {
        return getMetadata();
    }

    @Override
    public JSONB value9() {
        return getAnnotations();
    }

    @Override
    public ModulesRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public ModulesRecord value2(Long value) {
        setPackageVersionId(value);
        return this;
    }

    @Override
    public ModulesRecord value3(Long value) {
        setModuleNameId(value);
        return this;
    }

    @Override
    public ModulesRecord value4(Boolean value) {
        setFinal(value);
        return this;
    }

    @Override
    public ModulesRecord value5(Access value) {
        setAccess(value);
        return this;
    }

    @Override
    public ModulesRecord value6(Long[] value) {
        setSuperClasses(value);
        return this;
    }

    @Override
    public ModulesRecord value7(Long[] value) {
        setSuperInterfaces(value);
        return this;
    }

    @Override
    public ModulesRecord value8(JSONB value) {
        setMetadata(value);
        return this;
    }

    @Override
    public ModulesRecord value9(JSONB value) {
        setAnnotations(value);
        return this;
    }

    @Override
    public ModulesRecord values(Long value1, Long value2, Long value3, Boolean value4, Access value5, Long[] value6, Long[] value7, JSONB value8, JSONB value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ModulesRecord
     */
    public ModulesRecord() {
        super(Modules.MODULES);
    }

    /**
     * Create a detached, initialised ModulesRecord
     */
    public ModulesRecord(Long id, Long packageVersionId, Long moduleNameId, Boolean final_, Access access, Long[] superClasses, Long[] superInterfaces, JSONB metadata, JSONB annotations) {
        super(Modules.MODULES);

        setId(id);
        setPackageVersionId(packageVersionId);
        setModuleNameId(moduleNameId);
        setFinal(final_);
        setAccess(access);
        setSuperClasses(superClasses);
        setSuperInterfaces(superInterfaces);
        setMetadata(metadata);
        setAnnotations(annotations);
    }
}
