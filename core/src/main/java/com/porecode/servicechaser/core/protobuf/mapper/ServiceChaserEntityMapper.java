package com.porecode.servicechaser.core.protobuf.mapper;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
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
public class ServiceChaserEntityMapper {

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
    setOptionalField(messageBuilder, "id", entity.getId());
    setOptionalField(messageBuilder, "int_value", entity.getIntValue());
    setOptionalField(messageBuilder, "text_value", entity.getTextValue());
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

  private static void setOptionalField(
      Message.Builder builder, String fieldName, Object value) {
    Descriptors.Descriptor d = builder.getDescriptorForType();
    try {
      if (value != null) {
        builder.setField(d.findFieldByName(fieldName), value);
      }
    } catch (NullPointerException e) {
      throw new IllegalStateException(
          String.format(
            "Field with name [%s] doesn't exist in message type [%s]", 
            fieldName, d.getName()),
          e);
    }
  }
  
}
