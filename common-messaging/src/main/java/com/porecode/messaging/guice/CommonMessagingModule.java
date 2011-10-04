package com.porecode.messaging.guice;

import com.google.inject.AbstractModule;

public final class CommonMessagingModule extends AbstractModule {

  @Override
  protected void configure() {
//    bind(MessageReceiver.class).to(JmsMessageReceiver.class);
//    bind(MessageSender.class).to(JmsMessageSender.class);
//
//    bindInterceptor(Matchers.any(),
//        Matchers.annotatedWith(LogEntering.class),
//        new LogInterceptor("Entering"));
//    bindInterceptor(Matchers.any(),
//        Matchers.annotatedWith(LogLeaving.class),
//        new LogInterceptor("Entering"));
  }
}
