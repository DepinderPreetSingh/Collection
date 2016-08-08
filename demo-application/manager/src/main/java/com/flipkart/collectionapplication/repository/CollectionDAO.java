package com.flipkart.collectionapplication.repository;

import com.google.inject.Inject;

import com.flipkart.collectionapplication.model.Collection;
import com.flipkart.collectionapplication.repository.iface.ICollectionDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;


@Slf4j
public class CollectionDAO extends AbstractDAO<Collection> implements ICollectionDAO {

  SessionFactory sessionFactory;
  /**
   * Creates a new DAO with a given session provider.
   *
   * @param sessionFactory a session provider
   */
  @Inject
  public CollectionDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
    this.sessionFactory = sessionFactory;
  }
  @Override
  public Collection createCollection(Collection collection)
  {
    Session session = sessionFactory.openSession();
    Transaction createTransaction = null;
    try
    {
      createTransaction = session.beginTransaction();
      session.saveOrUpdate(collection);
      createTransaction.commit();
    }
    catch (Exception e) {
      if (createTransaction != null) {
        createTransaction.rollback();
      }
      log.error("Exception occurred. " + e.getStackTrace().toString());
    }
    finally {
      session.close();
    }
    return collection;
  }
  @Override
  public Collection updateCollection(Collection collection)
  {
    Session session = sessionFactory.openSession();
    Transaction createTransaction = null;
    try
    {
      createTransaction = session.beginTransaction();
      Collection collection1=find(collection.getMerchant_transaction_id());
      //Collection collection1=(Collection)session.get(Collection.class,collection.getMerchant_transaction_id());
      collection1.setTransaction_status(collection.getTransaction_status());
      collection1.setUpdatedAt(collection.getUpdatedAt());
      session.update(collection1);

      //session.saveOrUpdate(collection);
      createTransaction.commit();
    }
    catch (Exception e) {
      if (createTransaction != null) {
        createTransaction.rollback();
      }
      log.error("Exception occurred. " + e.getStackTrace().toString());
    }
    finally {
      session.close();
    }
    return collection;
  }



  @Override
  public Collection find(String id) {
    Session session = sessionFactory.openSession();
    try
    {
      return (Collection) session.createCriteria(Collection.class).add(
          Restrictions.eq("merchant_transaction_id", id)).uniqueResult();
    }
    finally {
      session.close();
    }
  }

  @Override
  public List<Collection> findByName(String name) {
    Session session = sessionFactory.openSession();
    try {
      return (List<Collection>) session.createCriteria(Collection.class).add(
          Restrictions.eq("seller_id", name)).list();
    }
    finally {
      session.close();
    }
  }

  /*@Override
  public Collection remove(String name)
  {
    Collection collection=findByName(name);
    Session session = sessionFactory.openSession();
    Transaction createTransaction = null;
    try
    {
      createTransaction = session.beginTransaction();
      session.delete(collection);
      createTransaction.commit();
    }
    catch (Exception e) {
      if (createTransaction != null) {
        createTransaction.rollback();
      }
      log.error("Exception occurred. " + e.getStackTrace().toString());
    }
    finally {
      session.close();
    }
    return collection;

  }*/

}
