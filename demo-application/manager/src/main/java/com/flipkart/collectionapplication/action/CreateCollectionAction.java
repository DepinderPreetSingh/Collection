package com.flipkart.collectionapplication.action;

/**
 * Created by nitish.agarwal on 30/11/15.
 */

import com.flipkart.collectionapplication.util.ResourceConflictException;
import com.google.inject.Inject;

import com.flipkart.collectionapplication.model.Collection;
import com.flipkart.collectionapplication.repository.iface.ICollectionDAO;
import com.flipkart.collectionapplication.request.CreateCollectionRequest;

import org.modelmapper.ModelMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateCollectionAction
{

  public static final String CONFLICT_MESSAGE_PREFIX = "Seller Id already exists with name - ";

  private final ICollectionDAO CollectionDAO;

  private CreateCollectionRequest request;

  @Inject
  public CreateCollectionAction(ICollectionDAO collectionDAO) {
    this.CollectionDAO = collectionDAO;
  }


  public Void invoke() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    Collection transaction = modelMapper.map(this.request, Collection.class);
    if(isCollectionExists())
    {
      //System.out.println("update");
      //transaction.onCreate();
      transaction.onUpdate();
      CollectionDAO.updateCollection(transaction);
    }
    else
    {
     // System.out.println("create");
      transaction.onCreate();
      CollectionDAO.createCollection(transaction);
    }
    return null;
  }

  public CreateCollectionAction withRequest(CreateCollectionRequest request) {
    this.request = request;
    return this;
  }

  private boolean isCollectionExists() {
    Collection collection = CollectionDAO.find(this.request.getMerchant_transaction_id());
    if(collection != null) {
      return true;
     // String message = CONFLICT_MESSAGE_PREFIX + this.request.getSeller_id();
      //throw new ResourceConflictException(null, message);
    }
    return false;
  }


}

