package com.virtualbank.enums;

public enum Providers {

  EVN("Tap doan dien luc Viet Nam"), VNPT("Tap doan buu chinh Vien Thong"), NHA_MAY_NUOC_DN(
      "Cong ty co phan cap nuoc Da Nang");

  private String detail;

  private Providers(String detail) {
    this.detail = detail;
  }

  public String getDetail() {
    return detail;
  }

}
