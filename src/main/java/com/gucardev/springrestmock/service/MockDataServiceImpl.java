package com.gucardev.springrestmock.service;

import com.gucardev.springrestmock.model.HttpMethod;
import com.gucardev.springrestmock.model.MockData;
import com.gucardev.springrestmock.model.ResponseType;
import com.gucardev.springrestmock.repository.MockDataRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
      existingMockData.setFailureStatus(mockData.getFailureStatus());
      existingMockData.setSuccessStatus(mockData.getSuccessStatus());
      existingMockData.setFailureResponse(mockData.getFailureResponse());
      existingMockData.setSuccessResponse(mockData.getSuccessResponse());
      existingMockData.setHttpMethod(mockData.getHttpMethod());
      existingMockData.setResponseType(mockData.getResponseType());
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

  @PostConstruct
  void setInitialData() {

    List<MockData> mockDataList = new ArrayList<>();

    mockDataList.add(
        MockData.builder()
            .responseType(ResponseType.SUCCESS)
            .path("/api/auth/login")
            .httpMethod(HttpMethod.POST)
            .successStatus(200)
            .failureStatus(401)
            .successResponse("successResponse1")
            .failureResponse("failureResponse1")
            .build());

    mockDataList.add(
        MockData.builder()
            .responseType(ResponseType.SUCCESS)
            .path("/api/auth/login")
            .httpMethod(HttpMethod.POST)
            .successStatus(200)
            .failureStatus(401)
            .successResponse("successResponse2")
            .failureResponse("failureResponse2")
            .build());

    mockDataList.add(
        MockData.builder()
            .responseType(ResponseType.FAILURE)
            .path("/api/auth/register")
            .httpMethod(HttpMethod.POST)
            .successStatus(200)
            .failureStatus(401)
            .successResponse("successResponse3")
            .failureResponse("failureResponse3")
            .build());

    mockDataList.add(
        MockData.builder()
            .responseType(ResponseType.RANDOM)
            .path("/api/user/1")
            .httpMethod(HttpMethod.DELETE)
            .successStatus(200)
            .failureStatus(404)
            .successResponse("successResponse4")
            .failureResponse("failureResponse4")
            .build());

    mockDataList.add(
        MockData.builder()
            .responseType(ResponseType.FAILURE)
            .path("/api/user/profile")
            .httpMethod(HttpMethod.PUT)
            .successStatus(200)
            .failureStatus(401)
            .successResponse("successResponse5")
            .failureResponse("failureResponse5")
            .build());

    mockDataRepository.saveAll(mockDataList);
  }
}
