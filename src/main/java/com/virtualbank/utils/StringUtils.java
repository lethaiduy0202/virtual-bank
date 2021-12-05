package com.virtualbank.utils;

public class StringUtils {
  private StringUtils() {}

  public static String joinStrings(String initString, String... inputStrings) {
    StringBuilder stringBuilder = new StringBuilder(initString);
    for (String inputString : inputStrings) {
      stringBuilder.append(inputString);
    }
    return stringBuilder.toString();
  }

  public static String removeLetters(String strOrigin, String oldString, String newString) {
    return strOrigin.replace(oldString, newString);
  }

}
