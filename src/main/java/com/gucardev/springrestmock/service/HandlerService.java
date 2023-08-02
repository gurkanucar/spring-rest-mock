package com.gucardev.springrestmock.service;

import com.gucardev.springrestmock.model.MockData;

import javax.servlet.http.HttpServletRequest;

public interface HandlerService {
    MockData getByRequest(HttpServletRequest request);
}
