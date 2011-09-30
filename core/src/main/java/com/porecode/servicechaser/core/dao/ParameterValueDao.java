package com.porecode.servicechaser.core.dao;

import com.porecode.servicechaser.core.hibernate.ParameterValue;

import java.util.List;

/**
 * {@link ParameterValue} data manipulations
 *
 * @see ParameterValue
 */
public interface ParameterValueDao {

  List<ParameterValue> selectAll();
}
