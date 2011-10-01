package com.porecode.servicechaser.core.dao;

import com.porecode.servicechaser.core.hibernate.ParameterValue;

import java.util.List;

/**
 * {@link ParameterValue} data manipulations
 *
 * @author filipovskii_off
 * @see ParameterValue
 */
public interface ParameterValueDao {

  /**
   * Selects all {@link ParameterValue} objects
   * @return
   */
  List<ParameterValue> selectAll();
}
