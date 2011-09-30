package com.porecode.servicechaser.core.protobuf.mapper;

import com.porecode.servicechaser.core.hibernate.ParameterValue;
import com.porecode.servicechaser.core.protobuf.EntityProtos;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test for Parameter Value Mapper
 * @author fealaer
 * @author filipovskii_off
 */
public class TestServiceChaserEntityMapper {

  private String strValue = "someString";
  private Long intValue = 3L;

  @Test
  public void testParamterValueToMessageMapping() throws Exception {
    ParameterValue entity = new ParameterValue();
    entity.setIntValue(intValue);
    entity.setTextValue(strValue);
    EntityProtos.ParameterValue message =
      ServiceChaserEntityMapper.parameterValueToMessage(entity);

    assertEquals(intValue, (Long) message.getIntValue());
    assertEquals(strValue, message.getTextValue());
  }

  @Test
  public void testNullParameterValueFields() throws Exception {
    ParameterValue entity = new ParameterValue();
    EntityProtos.ParameterValue message =
      ServiceChaserEntityMapper.parameterValueToMessage(entity);

    assertFalse(message.hasId());
    assertFalse(message.hasIntValue());
    assertFalse(message.hasTextValue());
  }

  @Test
  public void testMessageToParameterValue() throws Exception {
    EntityProtos.ParameterValue.Builder builder =
        EntityProtos.ParameterValue.newBuilder();
    builder.setId(intValue);
    builder.setIntValue(intValue);
    builder.setTextValue(strValue);

    EntityProtos.ParameterValue parameterValue = builder.build();

    ParameterValue entity =
      ServiceChaserEntityMapper.parameterValueToEntity(parameterValue);

    assertEquals(intValue, entity.getId());
    assertEquals(intValue, entity.getIntValue());
    assertEquals(strValue, entity.getTextValue());
  }
}
