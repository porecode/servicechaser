package com.porecode.messaging.guice;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.porecode.common.interceptor.logger.LogEntering;
import com.porecode.common.interceptor.logger.LogInterceptor;
import com.porecode.common.interceptor.logger.LogLeaving;
import com.porecode.messaging.MessageReceiver;
import com.porecode.messaging.MessageSender;
import com.porecode.messaging.jms.JmsMessageReceiver;
import com.porecode.messaging.jms.JmsMessageSender;

public class CommonMessagingModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(MessageReceiver.class).to(JmsMessageReceiver.class);
    bind(MessageSender.class).to(JmsMessageSender.class);

    bindInterceptor(Matchers.any(),
        Matchers.annotatedWith(LogEntering.class),
        new LogInterceptor("Entering"));
    bindInterceptor(Matchers.any(),
        Matchers.annotatedWith(LogLeaving.class),
        new LogInterceptor("Entering"));
  }
}
