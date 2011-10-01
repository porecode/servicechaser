package com.porecode.servicechaser.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.protobuf.socketrpc.RpcServer;
import com.googlecode.protobuf.socketrpc.ServerRpcConnectionFactory;
import com.googlecode.protobuf.socketrpc.SocketRpcConnectionFactories;
import com.porecode.servicechaser.core.guice.ProtobufServiceModule;
import com.porecode.servicechaser.core.protobuf.CoreServices;

import java.util.concurrent.Executors;

public final class CoreMain {

  public static final void main(String... args) {
    Injector inj = Guice.createInjector(new ProtobufServiceModule());

    ServerRpcConnectionFactory rpcConnectionFactory =
        SocketRpcConnectionFactories
        .createServerRpcConnectionFactory(7777);

    RpcServer server = new RpcServer(rpcConnectionFactory,
        Executors.newFixedThreadPool(1), true);

    server.registerService(inj.getInstance(
        CoreServices.ParameterValueService.class)); // For non-blocking impl

//    server.registerBlockingService(MyService
//        .newReflectiveBlockingService(new MyBlockingInterfaceImpl())); // For blocking impl
    server.run();
  }
}
