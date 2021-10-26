/*
 * This file is generated by jOOQ.
 */
package eu.fasten.core.data.metadatadb.codegen.routines;


import eu.fasten.core.data.metadatadb.codegen.Public;

import java.util.UUID;

import javax.annotation.processing.Generated;

import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.14.15"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GenRandomUuid extends AbstractRoutine<UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>public.gen_random_uuid.RETURN_VALUE</code>.
     */
    public static final Parameter<UUID> RETURN_VALUE = Internal.createParameter("RETURN_VALUE", SQLDataType.UUID, false, false);

    /**
     * Create a new routine call instance
     */
    public GenRandomUuid() {
        super("gen_random_uuid", Public.PUBLIC, SQLDataType.UUID);

        setReturnParameter(RETURN_VALUE);
    }
}
