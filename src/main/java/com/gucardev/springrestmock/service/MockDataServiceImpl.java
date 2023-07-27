package com.gucardev.springrestmock.service;

import com.gucardev.springrestmock.model.MockData;
import com.gucardev.springrestmock.repository.MockDataRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MockDataServiceImpl implements MockDataService {

  private final MockDataRepository mockDataRepository;

  public MockDataServiceImpl(MockDataRepository mockDataRepository) {
    this.mockDataRepository = mockDataRepository;
  }

  @Override
  public List<MockData> findAll() {
    return mockDataRepository.findAll();
  }

  @Override
  public MockData findById(Long id) {
    return mockDataRepository.findById(id).orElse(null);
  }

  @Override
  public MockData save(MockData mockData) {
    return mockDataRepository.save(mockData);
  }

  @Override
  public MockData update(Long id, MockData mockData) {
    MockData existingMockData = mockDataRepository.findById(id).orElse(null);
    if (existingMockData != null) {
      existingMockData.setPath(mockData.getPath());
      // Update other properties as needed
      return mockDataRepository.save(existingMockData);
    } else {
      return null;
    }
  }

  @Override
  public boolean delete(Long id) {
    if (mockDataRepository.existsById(id)) {
      mockDataRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
