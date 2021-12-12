package com.virtualbank.enums;

public enum ErrorsEnum {
  ROLE_EXIST("Role is exist"),
  ACCOUNT_EXIST("Account is exist"),
  AUTHEN_FAIL("Username or password incorrect");

  private String errorMessage;

  ErrorsEnum(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
