package com.porecode.servicechaser.core.protobuf.mapper;

import com.porecode.servicechaser.core.hibernate.ParameterValue;
import com.porecode.servicechaser.core.protobuf.EntityProtos;

/**
 * Protobuf message converter
 * <p/>
 * Created by IntelliJ IDEA.
 * User: fealaer
 * Date: 28.09.11
 * Time: 21:09
 */
public class ParameterValueMapper {

  /**
   * Converts {@link ParameterValue} entity to protobuf message
   *
   * @param entity
   * @return
   */
  public static EntityProtos.ParameterValue toMessage(ParameterValue entity) {
    if (entity == null)
      throw new IllegalStateException("Entity cannot be null");

    EntityProtos.ParameterValue.Builder messageBuilder =
        EntityProtos.ParameterValue.newBuilder();
    messageBuilder.setId(entity.getId());
    messageBuilder.setIntValue(entity.getIntValue());
    messageBuilder.setTextValue(entity.getTextValue());
    return messageBuilder.build();
  }

  public static ParameterValue toEntity(
      EntityProtos.ParameterValue parameterValue) {
    if (parameterValue == null)
      throw new IllegalStateException("Message cannot be null");

    ParameterValue entity = new ParameterValue();
    entity.setId(parameterValue.getId());
    entity.setTextValue(parameterValue.getTextValue());
    entity.setIntValue(parameterValue.getIntValue());
    return entity;
  }
}
