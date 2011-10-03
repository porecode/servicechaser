package com.porecode.servicechaser.webapi.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.protobuf.BlockingRpcChannel;
import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;
import com.googlecode.protobuf.format.JsonFormat;
import com.googlecode.protobuf.socketrpc.RpcChannels;
import com.googlecode.protobuf.socketrpc.RpcConnectionFactory;
import com.googlecode.protobuf.socketrpc.SocketRpcConnectionFactories;
import com.googlecode.protobuf.socketrpc.SocketRpcController;
import com.porecode.rpc.guice.CoreRpcModule;
import com.porecode.rpc.protobuf.CoreServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class ParameterValueServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Injector inj = Guice.createInjector(new CoreRpcModule());
    BlockingRpcChannel channel = inj.getInstance(BlockingRpcChannel.class);

    // Call service
    CoreServices.ParameterValueService.BlockingInterface service =
        CoreServices.ParameterValueService.newBlockingStub(channel);
    RpcController controller = new SocketRpcController();

    CoreServices.ParameterValueResponse serviceResp = null;
    try {
      serviceResp = service.listAll(
          controller,
          CoreServices.ParameterValueRequest.newBuilder().build());
    } catch (ServiceException e) {
      e.printStackTrace();
    }

    // Check success
    if (controller.failed()) {
      System.err.println(
          String.format("Rpc failed %s", controller.errorText()));
    } else {
      resp.getOutputStream().println(JsonFormat.printToString(serviceResp));
    }
  }
}
