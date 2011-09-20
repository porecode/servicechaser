package com.porecode.common.interceptor.logger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Log method leaving
 * <b>value</b> - Format string for output param
 * @see LogInterceptor
 */
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD)
public @interface LogLeaving {
  String value();
}
