package com.gucardev.springrestmock.service;

import com.gucardev.springrestmock.model.ContentType;
import com.gucardev.springrestmock.model.HttpMethod;
import com.gucardev.springrestmock.model.MockData;
import com.gucardev.springrestmock.model.ResponseType;
import com.gucardev.springrestmock.repository.MockDataRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

  @PostConstruct
  void setInitialData() {

    List<MockData> mockDataList = new ArrayList<>();
    mockDataList.add(
        MockData.builder()
            .responseType(ResponseType.SUCCESS)
            .path("/api/entries/1")
            .contentType(ContentType.REST)
            .httpMethod(HttpMethod.GET)
            .successStatus(200)
            .failureStatus(401)
            .successResponse(
                "{\n"
                    + "  \"userId\": 1,\n"
                    + "  \"id\": 1,\n"
                    + "  \"title\": \"delectus aut autem\",\n"
                    + "  \"completed\": false\n"
                    + "}")
            .failureResponse("failureResponse1")
            .build());
    mockDataList.add(
        MockData.builder()
            .responseType(ResponseType.SUCCESS)
            .path("/api/auth/login")
            .delay(3000)
            .httpMethod(HttpMethod.POST)
            .contentType(ContentType.REST)
            .successStatus(200)
            .failureStatus(401)
            .successResponse("successResponse1")
            .failureResponse("failureResponse1")
            .build());

    mockDataList.add(
        MockData.builder()
            .responseType(ResponseType.FAILURE)
            .contentType(ContentType.REST)
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
            .contentType(ContentType.REST)
            .failureStatus(404)
            .successResponse("successResponse4")
            .failureResponse("failureResponse4")
            .build());

    mockDataList.add(
        MockData.builder()
            .responseType(ResponseType.RANDOM)
            .path("/api/user/profile")
            .delay(5000)
            .httpMethod(HttpMethod.POST)
            .contentType(ContentType.SOAP)
            .successStatus(200)
            .failureStatus(401)
            .successResponse(
                "<soap:Envelope xmlns:soap='http://www.w3.org/2003/05/soap-envelope'>\n"
                    + "    <soap:Body>\n"
                    + "        <users>\n"
                    + "            <user>\n"
                    + "                <id>1</id>\n"
                    + "                <name>grkn</name>\n"
                    + "            </user>\n"
                    + "            <user>\n"
                    + "                <id>2</id>\n"
                    + "                <name>ali</name>\n"
                    + "            </user>\n"
                    + "            <user>\n"
                    + "                <id>3</id>\n"
                    + "                <name>veli</name>\n"
                    + "            </user>\n"
                    + "        </users>\n"
                    + "    </soap:Body>\n"
                    + "</soap:Envelope>")
            .failureResponse(
                "<env:Envelope xmlns:env=http://www.w3.org/2003/05/soap-envelope>\n"
                    + " <env:Body>\n"
                    + " <env:Fault>\n"
                    + " <Fault subelements>\n"
                    + " </env:Fault>\n"
                    + " </env:Body>\n"
                    + "</env:Envelope>")
            .build());

    mockDataRepository.saveAll(mockDataList);
  }
}
