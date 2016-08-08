package com.flipkart.collectionapplication.action;

/**
 * Created by nitish.agarwal on 30/11/15.
 */

import com.google.inject.Inject;

import com.flipkart.collectionapplication.model.Collection;
import com.flipkart.collectionapplication.repository.iface.ICollectionDAO;
import com.flipkart.collectionapplication.response.ListCollectionResponse;
import com.flipkart.collectionapplication.util.ResourceNotFoundException;


import org.modelmapper.ModelMapper;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FetchCollectionAction {

  public static final String RESOURCE_NOT_FOUND_MESSAGE = "No  seller  found by id - ";

  private final ICollectionDAO collectionDAO;

  private String name;  //name here is merchant id
  private List<ListCollectionResponse.Collection> response=new ArrayList<ListCollectionResponse.Collection>();;

  @Inject
  public FetchCollectionAction(ICollectionDAO collectionDAO) {
    this.collectionDAO = collectionDAO;
  }


  public List<ListCollectionResponse.Collection> invoke() {
    List<Collection> collection = getCollection();
    ModelMapper modelMapper = new ModelMapper();
    for(int i=0;i<collection.size();i++) {
     // collection.get(i).created.toString();
      response.add(modelMapper.map(collection.get(i), ListCollectionResponse.Collection.class));
      //response[i].created=response[i].created.toString();
    }
    return response;
  }

  public FetchCollectionAction withName(String name) {
    this.name = name;
    return this;
  }

  private List<Collection> getCollection() {
    List<Collection> collection = collectionDAO.findByName(this.name);
    if(collection == null) {
      String message = RESOURCE_NOT_FOUND_MESSAGE + this.name;
      throw new ResourceNotFoundException(message);
    }
    return collection;
  }


}

