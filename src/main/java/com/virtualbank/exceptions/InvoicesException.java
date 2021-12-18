package com.virtualbank.exceptions;

public class InvoicesException extends Exception {

  private static final long serialVersionUID = 1L;

  public InvoicesException() {
    super();
  }

  public InvoicesException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public InvoicesException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvoicesException(String message) {
    super(message);
  }

  public InvoicesException(Throwable cause) {
    super(cause);
  }


}
