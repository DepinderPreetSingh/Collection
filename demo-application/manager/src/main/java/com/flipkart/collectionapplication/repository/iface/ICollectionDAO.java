package com.flipkart.collectionapplication.repository.iface;

import com.flipkart.collectionapplication.model.Collection;

import java.util.List;

public interface ICollectionDAO {

    Collection createCollection(Collection context);
    Collection updateCollection(Collection context);

    Collection find(String id);
    //Collection isCollectionExists(Collection collection);
    //Collection remove(String name);

    /**
     * Finds the employee given the name of the employee
     * @param name
     * @return
     */
    public List<Collection> findByName(String name);

}
