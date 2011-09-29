package com.porecode.servicechaser.core.protobuf.mapper;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;

final class CommonFunctions {

  /**
   * Calls {@link
   * #setOptionalField(com.google.protobuf.Message.Builder, String, Object, boolean)}
   * with silentFault = <b>false</b>
   *
   * @param builder
   * @param fieldName
   * @param value
   */
  public static void setOptionalField(
      Message.Builder builder, String fieldName, Object value) {
    setOptionalField(builder, fieldName, value, false);
  }

  /**
   * Sets value of optional field in <b>builder</b> to <b>value</b><br/>
   * Throws {@link IllegalStateException}, if field with <b>fieldName</b><br/>
   * is not found in message and <b>silentFault</b> is set to <b>false</b>
   *
   * @param builder
   * @param fieldName
   * @param value
   * @param silentFault
   */
  public static void setOptionalField(
      Message.Builder builder, String fieldName,
      Object value, boolean silentFault) {
    Descriptors.Descriptor d = builder.getDescriptorForType();
    try {
      if (value != null) {
        builder.setField(d.findFieldByName(fieldName), value);
      }
    } catch (NullPointerException e) {
      if (!silentFault) {
        throw new IllegalStateException(
          String.format(
            "Field with name [%s] doesn't exist in message type [%s]",
            fieldName, d.getName()),
          e);
      }
    }
  }
}
