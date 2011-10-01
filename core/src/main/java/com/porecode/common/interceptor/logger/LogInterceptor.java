package com.porecode.common.interceptor.logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;

/**
 * Log interceptor.<br/>
 * Handles {@link LogEntering} and {@link LogLeaving} annotations
 *
 * @author filipovskii_off
 * @see #logInfo(String, Object[], String, org.apache.commons.logging.Log)
 * @see #logError(String, Throwable, org.apache.commons.logging.Log) 
 */
public class LogInterceptor implements MethodInterceptor {

  private final String startText;

  public LogInterceptor(String startText) {
    this.startText = startText;
  }

  @Override
  public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    Method method = methodInvocation.getMethod();
    Log log = LogFactory.getLog(method.getDeclaringClass());
    StringBuilder b = new StringBuilder();

    b.append(startText);
    b.append(String.format(" %s.%s ",
        method.getDeclaringClass().getSimpleName(),
        method.getName()));
    String startText = b.toString();
    LogEntering entering = method.getAnnotation(LogEntering.class);
    if (entering != null) {
      logInfo(startText, methodInvocation.getArguments(), entering.value(), log);
    }

    Object ret = null;
    try {
      ret = methodInvocation.proceed();
    } catch (Throwable e) {
      logError(startText, e, log);
      throw e;
    }


    LogLeaving leaving = method.getAnnotation(LogLeaving.class);
    if (leaving != null) {
      logInfo(startText, new Object[]{ret}, leaving.value(), log);
    }
    return ret;
  }

  /**
   * Logs on info level
   *
   * @param start start of log string (e.g. "Entering ClassName.methodName")
   * @param args arguments of the method call or return value
   * @param annotationValue parameters representation format ({@link LogEntering#value()})
   * @param log Log object
   */
  private final void logInfo(String start, Object[] args, String annotationValue, Log log) {
    StringBuilder b = new StringBuilder();
    b.append(start);
    if (StringUtils.isEmpty(annotationValue)) {
      b.append(StringUtils.join(args, ", "));
    }
    b.append(String.format(annotationValue, args));
    b.append("]");
    log.info(b);
  }

  /**
   * Logs on warn level
   *
   * @param start start of log string (e.g. "Entering ClassName.methodName")
   * @param e Exception
   * @param log Log object
   */
  private final void logError(String start, Throwable e, Log log) {
    log.warn("Exception occured: " + e);
  }

}
