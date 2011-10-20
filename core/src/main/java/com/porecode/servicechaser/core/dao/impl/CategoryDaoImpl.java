package com.porecode.servicechaser.core.dao.impl;

import com.porecode.servicechaser.core.dao.CategoryDao;
import com.porecode.servicechaser.core.hibernate.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author fealaer
 */
public class CategoryDaoImpl implements CategoryDao {
  private SessionFactory sessionFactory;

  @Inject
  public CategoryDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  /**
   * Selects all {@link com.porecode.servicechaser.core.hibernate.Category} objects
   *
   * @param {int} parent_id
   * @return {List} request result
   */
  @Override
  public List<Category> selectAllChildren(int parent_id) {
    Session session = sessionFactory.getCurrentSession();
    Transaction trans = session.beginTransaction();
    try {
      Criteria criteria = session.createCriteria(Category.class);
      criteria.add(Restrictions.eq("parent_id", parent_id));
      return criteria.list();
    } finally {
      trans.commit();
    }
  }
}
