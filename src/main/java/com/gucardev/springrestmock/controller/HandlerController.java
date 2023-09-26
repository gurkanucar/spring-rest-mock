package com.gucardev.springrestmock.controller;

import com.gucardev.springrestmock.model.MockData;
import com.gucardev.springrestmock.service.HandlerService;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("${mock.api.handler.endpoint}")
public class HandlerController {

  private final HandlerService handlerService;

  public HandlerController(HandlerService handlerService) {
    this.handlerService = handlerService;
  }

  @RequestMapping("/**")
  public ResponseEntity<?> handleRequest(HttpServletRequest request) throws InterruptedException {
    MockData mockData = handlerService.getByRequest(request);
    long delayMillis = mockData.getDelay();
    if (delayMillis > 0) {
      TimeUnit.MILLISECONDS.sleep(delayMillis);
    }
    return ResponseEntity.status(mockData.getChosenStatus())
        .contentType(mockData.getMediaType())
        .body(mockData.getChosenResponse());
  }
}
