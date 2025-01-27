/*
 * This file is generated by jOOQ.
 */
package eu.fasten.core.data.metadatadb.codegen.tables.records;


import eu.fasten.core.data.metadatadb.codegen.tables.VulnerabilitiesXPackageVersions;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


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
public class VulnerabilitiesXPackageVersionsRecord extends TableRecordImpl<VulnerabilitiesXPackageVersionsRecord> implements Record2<Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>public.vulnerabilities_x_package_versions.vulnerability_id</code>.
     */
    public void setVulnerabilityId(Long value) {
        set(0, value);
    }

    /**
     * Getter for
     * <code>public.vulnerabilities_x_package_versions.vulnerability_id</code>.
     */
    public Long getVulnerabilityId() {
        return (Long) get(0);
    }

    /**
     * Setter for
     * <code>public.vulnerabilities_x_package_versions.package_version_id</code>.
     */
    public void setPackageVersionId(Long value) {
        set(1, value);
    }

    /**
     * Getter for
     * <code>public.vulnerabilities_x_package_versions.package_version_id</code>.
     */
    public Long getPackageVersionId() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Long, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return VulnerabilitiesXPackageVersions.VULNERABILITIES_X_PACKAGE_VERSIONS.VULNERABILITY_ID;
    }

    @Override
    public Field<Long> field2() {
        return VulnerabilitiesXPackageVersions.VULNERABILITIES_X_PACKAGE_VERSIONS.PACKAGE_VERSION_ID;
    }

    @Override
    public Long component1() {
        return getVulnerabilityId();
    }

    @Override
    public Long component2() {
        return getPackageVersionId();
    }

    @Override
    public Long value1() {
        return getVulnerabilityId();
    }

    @Override
    public Long value2() {
        return getPackageVersionId();
    }

    @Override
    public VulnerabilitiesXPackageVersionsRecord value1(Long value) {
        setVulnerabilityId(value);
        return this;
    }

    @Override
    public VulnerabilitiesXPackageVersionsRecord value2(Long value) {
        setPackageVersionId(value);
        return this;
    }

    @Override
    public VulnerabilitiesXPackageVersionsRecord values(Long value1, Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VulnerabilitiesXPackageVersionsRecord
     */
    public VulnerabilitiesXPackageVersionsRecord() {
        super(VulnerabilitiesXPackageVersions.VULNERABILITIES_X_PACKAGE_VERSIONS);
    }

    /**
     * Create a detached, initialised VulnerabilitiesXPackageVersionsRecord
     */
    public VulnerabilitiesXPackageVersionsRecord(Long vulnerabilityId, Long packageVersionId) {
        super(VulnerabilitiesXPackageVersions.VULNERABILITIES_X_PACKAGE_VERSIONS);

        setVulnerabilityId(vulnerabilityId);
        setPackageVersionId(packageVersionId);
    }
}
