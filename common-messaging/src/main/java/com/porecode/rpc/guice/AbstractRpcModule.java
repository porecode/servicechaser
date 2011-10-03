package com.porecode.rpc.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.google.protobuf.BlockingRpcChannel;
import com.google.protobuf.RpcChannel;
import com.googlecode.protobuf.socketrpc.*;

import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executors;

/**
 * Common @Provides methods
 * for implementing Protocol Buffer services
 *
 * @see <a href="http://code.google.com/p/protobuf-socket-rpc/wiki/JavaUsage">
 *   protobuf-socket-rpc site</a>
 * @author filipovskii_off
 */
abstract class AbstractRpcModule extends AbstractModule {

  /**
   * @return relative path to rpc properties file, that should be in class path
   */
  protected abstract String getPropertiesFileName();

  @Override
  protected void configure() {
    Names.bindProperties(binder(), getProperties());
  }

  /**
   * Gets an {@link RpcServer}
   * Which then can be used to register service implementations.
   *<p>For example:
   * <pre>
   *   server.registerService(someService);
   *   server.run();
   * </pre>
   *</p>
   * @param port
   * @param poolSize
   * @return rpc server
   *
   * @see <a href="http://code.google.com/p/protobuf-socket-rpc/wiki/JavaUsage">
   *   protobuf-socket-rpc site</a>
   */
  @Inject
  @Provides
  RpcServer getRpcServer(
      @Named("rpc.server.port") int port,
      @Named("rpc.server.pool-size") int poolSize) {

    ServerRpcConnectionFactory rpcConnectionFactory =
        SocketRpcConnectionFactories
            .createServerRpcConnectionFactory(port);

    RpcServer server = new RpcServer(rpcConnectionFactory,
        Executors.newFixedThreadPool(poolSize), true);
    return server;
  }

  /**
   * Creates connection factory for client
   *
   * @param serverName
   * @param port
   * @return connection factory
   */
  @Inject
  @Provides
  RpcConnectionFactory getClientConnectionFactory(
      @Named("rpc.server.name") String serverName,
      @Named("rpc.server.port") int port){

    return SocketRpcConnectionFactories
        .createRpcConnectionFactory(serverName, port);
  }

  /**
   * Creates blocking client proxy implementation.
   * Which then can be used like this:
   * <pre>
   *   SomeService someService = SomeService.newBlockingStub(<b>channel</b>);
   *   someService.someMethod(...);
   * </pre>
   * @param connectionFactory
   * @return blocking rpc channel
   *
   * @see <a href="http://code.google.com/p/protobuf-socket-rpc/wiki/JavaUsage">
   *   protobuf-socket-rpc site</a>
   */
  @Inject
  @Provides
  BlockingRpcChannel getBlockingRpcChannel(
      RpcConnectionFactory connectionFactory) {
    return RpcChannels.newBlockingRpcChannel(connectionFactory);
  }

  /**
   * Creates blocking client proxy implementation.
   * Which then can be used like this:
   * <pre>
   *   MyService myService = MyService.newStub(<b>channel</b>);
   * </pre>
   * @param connectionFactory
   * @param poolSize
   * @return non-blocking rpc channel
   *
   * @see <a href="http://code.google.com/p/protobuf-socket-rpc/wiki/JavaUsage">
   *   protobuf-socket-rpc site</a>
   */
  @Inject
  @Provides
  RpcChannel getNonBlockingRpcChannel(
      RpcConnectionFactory connectionFactory,
      @Named("rpc.client.pool-size") int poolSize) {
    return RpcChannels.newRpcChannel(
        connectionFactory, Executors.newFixedThreadPool(poolSize));
  }

  Properties getProperties() {
    Properties properties = new Properties();
    try {
      InputStream is =
          this.getClass().getClassLoader().getResourceAsStream(getPropertiesFileName());
      properties.load(is);
      return properties;
    } catch (IOException e) {
      throw new IllegalStateException("No resources in " + getPropertiesFileName(), e);
    }
  }

}
