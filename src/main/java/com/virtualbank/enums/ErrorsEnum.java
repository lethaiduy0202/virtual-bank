package com.virtualbank.enums;

public enum ErrorsEnum {
  ROLE_EXIST("Role is exist");

  private String errorMessage;

  ErrorsEnum(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
