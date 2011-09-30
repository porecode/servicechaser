package com.porecode.servicechaser.core.protobuf.mapper;

import com.porecode.servicechaser.core.protobuf.EntityProtos;
import com.porecode.servicechaser.core.hibernate.ParameterValue;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestReflectiveMapper {
  
  private static final Long intValue = 1L;
  private static final String stringValue = "asdf";
  
  @Test 
  public void testParameterEntityValueReflectiveMap() throws Exception {
    ParameterValue entity = new ParameterValue();
    entity.setId(intValue);
    entity.setIntValue(intValue);
    entity.setTextValue(stringValue);

    EntityProtos.ParameterValue message =
        ReflectiveMapper.toMessageBuilder(
            entity,
            EntityProtos.ParameterValue.newBuilder()).build();

    assertEquals(intValue, (Long) message.getId());
    assertEquals(intValue, (Long) message.getIntValue());
    assertEquals(stringValue, message.getTextValue());
  }

  @Test
  public void testParameterValueMessageReflectiveMap() throws Exception {
    EntityProtos.ParameterValue m = EntityProtos.ParameterValue.newBuilder()
        .setId(intValue)
        .setIntValue(intValue)
        .setTextValue(stringValue).build();
    ParameterValue e = new ParameterValue();

    ReflectiveMapper.mergeMessageToEntity(m, e);

    assertEquals(intValue, e.getId());
    assertEquals(intValue, e.getIntValue());
    assertEquals(stringValue, e.getTextValue());
  }
}
