package com.gucardev.springrestmock.controller;

import com.gucardev.springrestmock.model.HttpMethod;
import com.gucardev.springrestmock.model.MockData;
import com.gucardev.springrestmock.service.MockDataService;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("${mock.api.handler.endpoint}")
public class HandlerController {

  @Value("${mock.api.handler.endpoint}")
  private String handlerEndpoint;

  private final MockDataService mockDataService;

  public HandlerController(MockDataService mockDataService) {
    this.mockDataService = mockDataService;
  }

  @RequestMapping("/**")
  public ResponseEntity<?> handleRequest(HttpServletRequest request) {
    String requestPath = request.getRequestURI();
    requestPath = requestPath.replace(handlerEndpoint, "");
    String requestMethod = request.getMethod();
    log.info("Request came to path: {} {}", requestPath, requestMethod);
    MockData mockData =
        mockDataService.findByPathAndMethod(requestPath, HttpMethod.getFromString(requestMethod));

    return ResponseEntity.status(mockData.getSuccessStatus()).body(mockData.getSuccessResponse());
  }
}
