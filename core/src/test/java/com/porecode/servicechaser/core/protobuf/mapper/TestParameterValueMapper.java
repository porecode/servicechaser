package com.porecode.servicechaser.core.protobuf.mapper;

import com.porecode.servicechaser.core.hibernate.ParameterValue;
import com.porecode.servicechaser.core.protobuf.EntityProtos;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.text.html.parser.Entity;

/**
 * Test for Parameter Value Mapper
 * @author fealaer
 * @author filipovskii_off
 */
public class TestParameterValueMapper {

  private String strValue = "someString";
  private Long intValue = 3L;

  @Test
  public void testEntityToMessageMapping() throws Exception {
    ParameterValue entity = new ParameterValue();
    entity.setIntValue(intValue);
    entity.setTextValue(strValue);
    EntityProtos.ParameterValue message = ParameterValueMapper.toMessage(entity);

    assertEquals(intValue, (Long) message.getIntValue());
    assertEquals(strValue, message.getTextValue());
  }

  @Test
  public void testNullEntityFields() throws Exception {
    ParameterValue entity = new ParameterValue();
    EntityProtos.ParameterValue message = ParameterValueMapper.toMessage(entity);

    assertEquals(-1, message.getId());
    assertEquals(0, message.getIntValue());
    assertEquals("", message.getTextValue());
  }

  @Test
  public void testMessageToEntity() throws Exception {
    EntityProtos.ParameterValue.Builder builder =
        EntityProtos.ParameterValue.newBuilder();
    builder.setId(intValue);
    builder.setIntValue(intValue);
    builder.setTextValue(strValue);

    EntityProtos.ParameterValue parameterValue = builder.build();

    ParameterValue entity = ParameterValueMapper.toEntity(parameterValue);

    assertEquals(intValue, entity.getId());
    assertEquals(intValue, entity.getIntValue());
    assertEquals(strValue, entity.getTextValue());
  }
}
