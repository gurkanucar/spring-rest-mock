package com.gucardev.springrestmock.model;

public enum HttpMethod {
  GET,
  POST,
  PUT,
  PATCH,
  DELETE;

  public static HttpMethod getFromString(String method) {
    for (HttpMethod value : HttpMethod.values()) {
      if (value.name().equals(method)) {
        return value;
      }
    }
    throw new RuntimeException("method not found!");
  }
}
