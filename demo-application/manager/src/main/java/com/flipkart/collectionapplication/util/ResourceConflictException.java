package com.flipkart.collectionapplication.util;

/**
 * Created by nitish.agarwal on 30/11/15.
 */
import java.net.URI;

import lombok.Getter;

/**
 * Thrown to return a 409 Conflict response with optional Location header and entity.
 */
@Getter
public class ResourceConflictException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private URI location;

  private Object entity;

  public ResourceConflictException()
  {
    this(null, null);
  }

  /**
   *
   * @param location URI of conflicting resource.
   */
  public ResourceConflictException(URI location)
  {
    this(location, null);
  }

  /**
   *
   * @param location URI of conflicting resource.
   * @param entity Entity
   */
  public ResourceConflictException(URI location, Object entity)
  {
    this.location = location;
    this.entity = entity;
  }

}

