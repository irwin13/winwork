package com.irwin13.winwork.basic.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author irwin Timestamp : 05/02/13 14:24
 *
 * To mark temporary for Class, Method or Field. An object marked with this annotation means that the object
 * need to be reviewed, refactor, or removed.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Temporary {
}
