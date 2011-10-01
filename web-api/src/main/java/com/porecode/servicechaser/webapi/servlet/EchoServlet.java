package com.porecode.servicechaser.webapi.servlet;

import com.google.inject.Injector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Singleton
public class EchoServlet extends HttpServlet {

  private static final Log log = LogFactory.getLog(EchoServlet.class);

  /**
   * Will be injected automatically by {@link com.porecode.servicechaser.webapi.guice.WebApiServletConfig}
   * @see <a href="http://code.google.com/p/google-guice/wiki/Servlets">Guice servlet tutorial</a>
   */
  @Inject
  private Injector injector;

//  @LogEntering
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String responseText = "You are trying to reach " + request.getRequestURI();
    PrintWriter writer = response.getWriter();
    writer.println(responseText);
    writer.close();
  }
}
