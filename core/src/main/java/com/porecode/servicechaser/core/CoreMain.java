package com.porecode.servicechaser.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.protobuf.socketrpc.RpcServer;
import com.googlecode.protobuf.socketrpc.ServerRpcConnectionFactory;
import com.googlecode.protobuf.socketrpc.SocketRpcConnectionFactories;
import com.porecode.rpc.guice.CoreRpcModule;
import com.porecode.rpc.protobuf.CoreServices;
import com.porecode.servicechaser.core.guice.ProtobufServiceModule;

import java.util.concurrent.Executors;

/**
 * Starts protocol buffer socket server
 *
 * @author filipovskii_off
 */
public final class CoreMain {

  public static final void main(String... args) {
    Injector inj = Guice.createInjector(
        new ProtobufServiceModule(),
        new CoreRpcModule());

    RpcServer server = inj.getInstance(RpcServer.class);
    server.registerService(inj.getInstance(
        CoreServices.ParameterValueService.class)); // For non-blocking impl

//    server.registerBlockingService(MyService
//        .newReflectiveBlockingService(new MyBlockingInterfaceImpl())); // For blocking impl
    server.run();
  }
}
