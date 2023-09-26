package com.gucardev.springrestmock.config;

import com.gucardev.springrestmock.model.MockData;
import com.gucardev.springrestmock.service.MockDataService;
import com.gucardev.springrestmock.util.JsonProcessorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Startup implements CommandLineRunner {

  private final JsonProcessorUtil jsonProcessorUtil;

  private final MockDataService mockDataService;

  @Override
  public void run(String... args) throws Exception {
    jsonProcessorUtil.processJson(
        "data/example.json",
        MockData.class,
        (x) -> {
          try {
            mockDataService.save(x);
          } catch (Exception e) {
            log.info("example data already exists");
          }
        });
  }
}
