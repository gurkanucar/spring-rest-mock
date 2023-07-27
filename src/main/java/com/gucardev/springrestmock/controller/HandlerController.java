package com.gucardev.springrestmock.controller;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mock")
public class HandlerController {

  @RequestMapping("/**")
  public ResponseEntity<?> handleRequest(HttpServletRequest request) {
    String requestPath = request.getRequestURI();
    String requestMethod = request.getMethod();
    log.info("Request came to path: {} {}", requestPath, requestMethod);
    return ResponseEntity.ok().build();
  }
}
