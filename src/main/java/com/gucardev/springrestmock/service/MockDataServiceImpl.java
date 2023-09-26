package com.gucardev.springrestmock.service;

import com.gucardev.springrestmock.model.HttpMethod;
import com.gucardev.springrestmock.model.MockData;
import com.gucardev.springrestmock.repository.MockDataRepository;
import com.gucardev.springrestmock.util.CurlConverter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MockDataServiceImpl implements MockDataService {

  private final MockDataRepository mockDataRepository;
  private final CurlConverter curlConverter;

  public MockDataServiceImpl(MockDataRepository mockDataRepository, CurlConverter curlConverter) {
    this.mockDataRepository = mockDataRepository;
    this.curlConverter = curlConverter;
  }

  @Override
  public List<MockData> findAll() {
    return mockDataRepository.findAll().stream()
        .peek(
            x -> {
              try {
                x.setCurlCommand(curlConverter.convertToCurl(x));
              } catch (Exception e) {
                throw new RuntimeException(e);
              }
            })
        .collect(Collectors.toList());
  }

  @Override
  public MockData findById(Long id) {
    return mockDataRepository.findById(id).orElse(null);
  }

  @Override
  public MockData findByPathAndMethod(String path, HttpMethod httpMethod) {
    return mockDataRepository
        .findMockDataByPathAndHttpMethod(path, httpMethod)
        .orElseThrow(() -> new RuntimeException("not found!"));
  }

  @Override
  public MockData save(MockData mockData) {
    if (mockDataRepository.existsMockDataByPathAndHttpMethod(
        mockData.getPath(), mockData.getHttpMethod())) {
      throw new RuntimeException("already exists!");
    }
    return mockDataRepository.save(mockData);
  }

  @Override
  public MockData update(Long id, MockData mockData) {
    MockData existingMockData = mockDataRepository.findById(id).orElse(null);
    Optional<MockData> samePathAndMethodMock =
        mockDataRepository.findMockDataByPathAndHttpMethod(
            mockData.getPath(), mockData.getHttpMethod());
    if (samePathAndMethodMock.isPresent() && !samePathAndMethodMock.get().getId().equals(id)) {
      throw new RuntimeException("already exists!");
    }

    if (existingMockData != null) {
      existingMockData.setPath(mockData.getPath());
      existingMockData.setDelay(mockData.getDelay());
      existingMockData.setContentType(mockData.getContentType());
      existingMockData.setFailureStatus(mockData.getFailureStatus());
      existingMockData.setSuccessStatus(mockData.getSuccessStatus());
      existingMockData.setFailureResponse(mockData.getFailureResponse());
      existingMockData.setSuccessResponse(mockData.getSuccessResponse());
      existingMockData.setHttpMethod(mockData.getHttpMethod());
      existingMockData.setResponseType(mockData.getResponseType());
      return mockDataRepository.save(existingMockData);
    } else {
      throw new RuntimeException("already exists!");
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
