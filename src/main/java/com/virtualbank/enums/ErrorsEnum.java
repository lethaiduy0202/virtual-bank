package com.virtualbank.enums;

public enum ErrorsEnum {
  ROLE_EXIST("Role is exist"),
  ACCOUNT_EXIST("Account is exist"),
  ACCOUNT_NON_EXIST("The account does not exist"),
  AUTHEN_FAIL("Username or password incorrect"),
  CANNOT_SAVE("Opps! The save is failed"),
  MONEY_NOT_ENOUGHT("Your money not enough");

  private String errorMessage;

  ErrorsEnum(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
