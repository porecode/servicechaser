package com.porecode.servicechaser.core.dao;

import com.porecode.servicechaser.core.hibernate.Category;

import java.util.List;

/**
 * {@link com.porecode.servicechaser.core.hibernate.Category} data manipulations
 *
 * @author fealaer
 * @see com.porecode.servicechaser.core.hibernate.Category
 */
public interface CategoryDao {

  /**
   * Selects all {@link com.porecode.servicechaser.core.hibernate.Category} objects
   * @return
   */
  List<Category> selectAllChildren(int parent_id);
}
