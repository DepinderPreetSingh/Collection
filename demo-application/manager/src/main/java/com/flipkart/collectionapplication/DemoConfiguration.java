package com.flipkart.collectionapplication;

/**
 * Created by nitish.agarwal on 26/11/15.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)

public class  DemoConfiguration extends Configuration
{

  @Valid
  @NotNull
  private DataSourceFactory databaseConfiguration = new DataSourceFactory();

}
