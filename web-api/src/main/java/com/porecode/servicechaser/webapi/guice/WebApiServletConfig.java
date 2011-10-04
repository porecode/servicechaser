package com.porecode.servicechaser.webapi.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.porecode.messaging.guice.CommonMessagingModule;
import com.porecode.servicechaser.webapi.servlet.ParameterValueServlet;

/**
 * @see <a href="http://code.google.com/p/google-guice/wiki/Servlets">Guice servlet tutorial</a>
 */
public final class WebApiServletConfig extends GuiceServletContextListener {
  
  @Override
  protected Injector getInjector() {
    return Guice.createInjector(
        new CommonMessagingModule(),
        new ServletModule() {

      @Override
      protected void configureServlets() {
//        serve("/*").with(EchoServlet.class);
        serve("/list_values/*").with(ParameterValueServlet.class);
      }
    });
  }
}
