package com.flipkart.collectionapplication.util;

/**
 * Created by nitish.agarwal on 30/11/15.
 */
import lombok.Getter;

/**
 * Thrown to return a 404 Not Found response with a message.
 */
@Getter
public class ResourceNotFoundException extends RuntimeException{

  private static final long serialVersionUID = 1L;

  private String message;

  public ResourceNotFoundException() {
    this("Resource not found.");
  }

  /**
   *
   * @param message Message.
   */
  public ResourceNotFoundException(String message) {
    this.message = message;
  }

}
