package com.porecode.servicechaser.core.dao.impl;

import com.google.inject.persist.Transactional;
import com.porecode.servicechaser.core.dao.CategoryDao;
import com.porecode.servicechaser.core.hibernate.Category;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import java.util.List;

/**
 *
 * @author fealaer
 */
public class CategoryDaoImpl implements CategoryDao {

  private EntityManager entityManager;

  @Inject
  public CategoryDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * Selects all {@link com.porecode.servicechaser.core.hibernate.Category} objects
   *
   * @param {int} parent_id
   * @return {List} request result
   */
  @Transactional
  @Override
  public List<Category> selectAllChildren(int parent_id) {
    CriteriaQuery<Category> q = entityManager.getCriteriaBuilder()
        .createQuery(Category.class);
    Root<Category> root = q.from(Category.class);
    EntityType<Category> Category_ = entityManager.getMetamodel().entity(Category.class);
    q.where(root.get(Category_.getSingularAttribute("parent")).in(parent_id));
    return entityManager.createQuery(q).getResultList();
  }
}
