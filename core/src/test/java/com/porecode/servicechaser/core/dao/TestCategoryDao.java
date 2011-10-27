package com.porecode.servicechaser.core.dao;

import com.porecode.servicechaser.core.dao.impl.CategoryDaoImpl;
import com.porecode.servicechaser.core.hibernate.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.junit.Test;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;

/**
 * @author fealaer
 */
public class TestCategoryDao {

  @Test
  public void testSelectChildrenEntities() throws Exception {
    SessionFactory sessionFactory = createMock(SessionFactory.class);
    Session session  = createMock(Session.class);
    Criteria criteria = createMock(Criteria.class);
    Transaction trans = createMock(Transaction.class);

    expect(sessionFactory.getCurrentSession()).andReturn(session);
    expect(session.beginTransaction()).andReturn(trans);
    expect(session.createCriteria(Category.class)).andReturn(criteria);
    expect(criteria.add(anyObject(Criterion.class))).andReturn(criteria);
    expect(criteria.list()).andReturn(new ArrayList<Category>());
    trans.commit();

    replay(sessionFactory);
    replay(session);
    replay(criteria);
    replay(trans);

    CategoryDao dao = new CategoryDaoImpl(null);
    dao.selectAllChildren(0);

    verify(sessionFactory);
    verify(session);
    verify(criteria);
    verify(trans);
  }
}
