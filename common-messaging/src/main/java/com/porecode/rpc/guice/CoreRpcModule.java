package com.porecode.rpc.guice;

/**
 * @author filipovskii_off
 */

/**
 * Guice module, that defines all bindings, required for
 * {@link com.porecode.rpc.protobuf.CoreServices} RPC.
 * <br/>
 * All settings are in {@link #propFile}
 * <br/>
 * Bindings are defined in {@link AbstractRpcModule}
 */
public final class CoreRpcModule extends AbstractRpcModule {

  public static final String propFile = "core-rpc.properties";

  @Override
  protected String getPropertiesFileName() {
    return propFile;
  }
}
