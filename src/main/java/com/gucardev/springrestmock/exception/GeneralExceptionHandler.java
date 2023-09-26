package com.gucardev.springrestmock.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleExceptions(
      Exception e, HttpServletRequest request) {
    var errorMap = new HashMap<String, Object>();
    String url =
        request.getRequestURI()
            + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
    errorMap.put("timestamp", new Date());
    errorMap.put("path", url);
    errorMap.put("method", request.getMethod());
    errorMap.put("message", e.getMessage());
    return ResponseEntity.status(400).body(errorMap);
  }
}
