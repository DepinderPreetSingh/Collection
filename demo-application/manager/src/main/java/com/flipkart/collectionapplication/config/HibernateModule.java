package com.flipkart.collectionapplication.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import com.flipkart.collectionapplication.DemoConfiguration;
import com.flipkart.collectionapplication.repository.CollectionDAO;
import com.flipkart.collectionapplication.repository.iface.ICollectionDAO;

import io.dropwizard.hibernate.HibernateBundle;


public class HibernateModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    private HibernateBundle<DemoConfiguration> hibernateBundle;

    public HibernateModule(HibernateBundle<DemoConfiguration> hibernate) {
        this.hibernateBundle = hibernate;
    }

    @Provides
    public ICollectionDAO provideCollectionRepo() {
        return new CollectionDAO(hibernateBundle.getSessionFactory());

    }
}
