package com.flipkart.collectionapplication.config;

/**
 * Created by nitish.agarwal on 26/11/15.
 */

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import com.flipkart.collectionapplication.CollectionApplication;
import com.flipkart.collectionapplication.DemoConfiguration;
import com.flipkart.collectionapplication.resource.CollectionResource;
import com.hubspot.dropwizard.guice.GuiceBundle;

import javax.inject.Singleton;

public class DemoModule extends AbstractModule {

  @Provides
  public GuiceBundle<DemoConfiguration> provideGuiceBundle() {
    return CollectionApplication.getGuiceBundle();
  }

  @Override
  protected void configure() {
    bind(CollectionResource.class).in(Singleton.class);
  }
}


