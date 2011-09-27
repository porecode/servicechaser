package com.porecode.servicechaser.webapi.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.porecode.messaging.guice.CommonMessagingModule;
import com.porecode.servicechaser.webapi.servlet.EchoServlet;

/**
 * @see <a href="http://code.google.com/p/google-guice/wiki/Servlets">Guice servlet tutorial</a>
 */
public class WebApiServletConfig extends GuiceServletContextListener {
  
  @Override
  protected Injector getInjector() {
    return Guice.createInjector(
        new CommonMessagingModule(),
        new ServletModule() {

      @Override
      protected void configureServlets() {
        serve("/*").with(EchoServlet.class);
      }
    });
  }
}
