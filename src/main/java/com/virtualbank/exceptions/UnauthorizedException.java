package com.virtualbank.exceptions;

public class UnauthorizedException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  
  private String message;

  public UnauthorizedException(String message) {
    super(message);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
