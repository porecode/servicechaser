package com.porecode.servicechaser.core.protobuf.mapper;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Converts any java {@link Object}
 * to protobuf {@link Message} and vice versa</br>.
 * Uses reflection to get object field values and determine message field names
 * 
 */
public final class ReflectiveMapper {

  private static final String GET = "get";
  private static final String SET = "set";
  private static final String UNDERLINE = "_";

  /**
   * Maps all values from entity fields with getters to builder
   *
   * @param entity
   * @param builder
   * @param <T> Builder Type
   * @return
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   *
   * @warning not tested with <b>repeated</b> message fields
   */
  public static <T extends Message.Builder> T toMessageBuilder(
      Object entity, T builder) {

    for (Method m : entity.getClass().getMethods()) {
      // all 'get'-methods
      if (m.getName().startsWith(GET)) {
        String varName = convertFromCamel(
            StringUtils.replaceOnce(m.getName(), GET, StringUtils.EMPTY));

        Object entityFieldValue = null;
        try {
          entityFieldValue = m.invoke(entity);
        } catch (Exception e) {
        }
        CommonFunctions.setOptionalField(
            builder, varName, entityFieldValue, true);
      }
    }

    return builder;
  }

  /**
   * Maps all message fields values to the given entity object
   *
   * @param message
   * @param entity
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   *
   * @warning not tested with <b>repeated</b> message fields
   */
  public static void mergeMessageToEntity(Message message, Object entity)
      throws InvocationTargetException, IllegalAccessException {
    Descriptors.Descriptor d = message.getDescriptorForType();

    for (Method m : entity.getClass().getMethods()) {
      String mName = m.getName();

      // all set methods
      if (mName.startsWith(SET)) {
        String fieldName = StringUtils.replaceOnce(mName, SET, StringUtils.EMPTY);
        try {
          Descriptors.FieldDescriptor fd = d.findFieldByName(
              convertFromCamel(fieldName));
          // try set entity field with value from message field
          m.invoke(entity, message.getField(fd));
        } catch (NullPointerException e) {
          // TODO: should we let this pass silently?
          // if there is no such field in message
        }
      }
    }
  }


  /**
   * Converts string from camelCase to underline_separated_string
   * @param str any string
   * @return 'some_text' if str was 'someText' or 'SomeText'
   */
  private static String convertFromCamel(String str) {
    str = StringUtils.uncapitalize(str);
    if (StringUtils.isAllLowerCase(str)) {
      return str;
    }
    StringBuilder builder = new StringBuilder(str);
    String[] chars = str.split(StringUtils.EMPTY);
    for (int i = 0; i < chars.length; i++) {
      if (StringUtils.isAllUpperCase(chars[i])) {
        builder.replace(i - 1, i, UNDERLINE);
        builder.insert(i, StringUtils.uncapitalize(chars[i]));
      }
    }
    return builder.toString();
  }

}
