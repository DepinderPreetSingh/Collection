package com.flipkart.collectionapplication;

import com.google.inject.Injector;
import com.google.inject.Stage;

import com.flipkart.collectionapplication.config.DemoModule;
import com.flipkart.collectionapplication.config.HibernateModule;
import com.flipkart.collectionapplication.model.Collection;
import com.flipkart.collectionapplication.repository.iface.ICollectionDAO;
import com.hubspot.dropwizard.guice.GuiceBundle;

import org.hibernate.cfg.Configuration;

import java.io.IOException;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CollectionApplication extends Application<DemoConfiguration> {
  public static void main(String[] args) throws Exception
  {
    new CollectionApplication().run(args);
  }

  @Override
  public String getName() {
    return "collection-application";
  }

  private static final HibernateBundle<DemoConfiguration> hibernate = new HibernateBundle<DemoConfiguration>(
      Collection.class) {
    @Override
    public DataSourceFactory getDataSourceFactory(DemoConfiguration configuration) {
      return configuration.getDatabaseConfiguration();
    }

    @Override
    public void configure(Configuration configuration) {
      configuration.setNamingStrategy(new CustomNamingStrategy());
    }
  };

  private final MigrationsBundle<DemoConfiguration> migrations = new MigrationsBundle<DemoConfiguration>() {
    @Override
    public DataSourceFactory getDataSourceFactory(DemoConfiguration configuration) {
      return configuration.getDatabaseConfiguration();
    }
  };


  private static final GuiceBundle<DemoConfiguration> guiceBundle = GuiceBundle.<DemoConfiguration>newBuilder()
      .addModule(new DemoModule())
      .addModule(new HibernateModule(hibernate))
      .setConfigClass(DemoConfiguration.class)
      .enableAutoConfig(DemoConfiguration.class.getPackage().getName())
      .build(Stage.DEVELOPMENT);


  @Override
  public void initialize(Bootstrap<DemoConfiguration> bootstrap) {
    bootstrap.addBundle(migrations);
    bootstrap.addBundle(hibernate);
    bootstrap.addBundle(guiceBundle);
  }


  public static GuiceBundle<DemoConfiguration> getGuiceBundle() {
    return guiceBundle;
  }

  @Override
  public void run(DemoConfiguration configuration,Environment environment) throws IOException {
    Injector injector = guiceBundle.getInjector();

    environment.jersey().register(injector.getInstance(ICollectionDAO.class));
  }


}