package com.gucardev.springrestmock.service;

import com.gucardev.springrestmock.model.HttpMethod;
import com.gucardev.springrestmock.model.MockData;
import com.gucardev.springrestmock.model.ResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class HandlerServiceImpl implements HandlerService {

    private final MockDataService mockDataService;

    public HandlerServiceImpl(MockDataService mockDataService) {
        this.mockDataService = mockDataService;
    }

    @Value("${mock.api.handler.endpoint}")
    private String handlerEndpoint;

    @Override
    public MockData getByRequest(HttpServletRequest request) {
        String url = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        log.info(url);
        url = url.replace(handlerEndpoint, "");

        String requestMethod = request.getMethod();
        log.info("Request came to path: {} {}", url, requestMethod);
        MockData mockData =
                mockDataService.findByPathAndMethod(url, HttpMethod.getFromString(requestMethod));

        if (mockData.getResponseType().equals(ResponseType.RANDOM)) {
            mockData.setResponseType(Math.random() > 0.5 ? ResponseType.SUCCESS : ResponseType.FAILURE);
        }
        if (mockData.getResponseType().equals(ResponseType.SUCCESS)) {
            mockData.setChosenResponse(mockData.getSuccessResponse());
            mockData.setChosenStatus(mockData.getSuccessStatus());
        } else if (mockData.getResponseType().equals(ResponseType.FAILURE)) {
            mockData.setChosenResponse(mockData.getFailureResponse());
            mockData.setChosenStatus(mockData.getFailureStatus());
        }
        return mockData;
    }
}
