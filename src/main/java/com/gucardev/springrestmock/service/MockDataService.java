package com.gucardev.springrestmock.service;

import com.gucardev.springrestmock.model.MockData;
import java.util.List;

public interface MockDataService {

  List<MockData> findAll();

  MockData findById(Long id);

  MockData save(MockData mockData);

  MockData update(Long id, MockData mockData);

  boolean delete(Long id);
}
