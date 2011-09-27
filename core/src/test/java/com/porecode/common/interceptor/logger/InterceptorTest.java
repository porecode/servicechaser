package com.porecode.common.interceptor.logger;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import org.junit.Test;

public class InterceptorTest {

  private class TestModule extends AbstractModule {

    @Override
    protected void configure() {
      bind(LogProducer.class);
      bindInterceptor(Matchers.any(),
          Matchers.annotatedWith(LogEntering.class),
          new LogInterceptor("Entering"));
    }
  }

  public static class LogProducer {

    public LogProducer() {
      
    }
    
    @LogEntering("param1=%s, param2=%s")
    public void produce(String param1, Object param2) {
      return;
    }
  }

  @Test
  public void testInterceptorLogs() throws Exception {
    Injector inj = Guice.createInjector(new TestModule());
    LogProducer lp = inj.getInstance(LogProducer.class);
    lp.produce("1 bla bla", "2 bla bla");
    // output should be on console
  }
}
