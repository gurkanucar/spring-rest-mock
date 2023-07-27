package com.gucardev.springrestmock.controller;

import com.gucardev.springrestmock.model.MockData;
import com.gucardev.springrestmock.service.MockDataService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/mock")
public class MockDataController {

  private final MockDataService mockDataService;

  public MockDataController(MockDataService mockDataService) {
    this.mockDataService = mockDataService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<MockData> getMockDataById(@PathVariable Long id) {
    MockData mockData = mockDataService.findById(id);
    if (mockData != null) {
      return ResponseEntity.ok(mockData);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<MockData>> getAllMockData() {
    List<MockData> mockDataList = mockDataService.findAll();
    return ResponseEntity.ok(mockDataList);
  }

  @PostMapping
  public ResponseEntity<MockData> createMockData(@RequestBody MockData mockData) {
    MockData createdMockData = mockDataService.save(mockData);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdMockData);
  }

  @PutMapping("/{id}")
  public ResponseEntity<MockData> updateMockData(
      @PathVariable Long id, @RequestBody MockData mockData) {
    MockData updatedMockData = mockDataService.update(id, mockData);
    if (updatedMockData != null) {
      return ResponseEntity.ok(updatedMockData);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMockData(@PathVariable Long id) {
    if (mockDataService.delete(id)) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
