package com.virtualbank.exceptions;

public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  public final String resourceName;
  public final String resourceField;

  public ResourceNotFoundException(String resourceName, String resourceField) {
    super(String.format("%s not found with %s", resourceName, resourceField));
    this.resourceField = resourceField;
    this.resourceName = resourceName;
  }

}
