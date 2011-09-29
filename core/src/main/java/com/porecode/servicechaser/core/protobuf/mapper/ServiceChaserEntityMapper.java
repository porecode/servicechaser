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
public final class ServiceChaserEntityMapper {

  private static final String ID_FIELD = "id";
  private static final String INT_VALUE_FIELD = "int_value";
  private static final String TEXT_VALUE_FIELD = "text_value";

  /**
   * Converts {@link ParameterValue} entity to protobuf message
   *
   * @param entity
   * @return
   */
  public static EntityProtos.ParameterValue parameterValueToMessage(
      ParameterValue entity) {
    if (entity == null)
      throw new IllegalStateException("Entity cannot be null");

    EntityProtos.ParameterValue.Builder messageBuilder =
        EntityProtos.ParameterValue.newBuilder();
    CommonFunctions.setOptionalField(
        messageBuilder, ID_FIELD, entity.getId());
    CommonFunctions.setOptionalField(
        messageBuilder, INT_VALUE_FIELD, entity.getIntValue());
    CommonFunctions.setOptionalField(
        messageBuilder, TEXT_VALUE_FIELD, entity.getTextValue());
    return messageBuilder.build();
  }

  public static ParameterValue parameterValueToEntity(
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
