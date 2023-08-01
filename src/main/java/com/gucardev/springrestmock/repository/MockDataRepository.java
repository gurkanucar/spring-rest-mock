package com.gucardev.springrestmock.repository;

import com.gucardev.springrestmock.model.HttpMethod;
import com.gucardev.springrestmock.model.MockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MockDataRepository extends JpaRepository<MockData, Long> {

    Optional<MockData> findMockDataByPathAndHttpMethod(String path, HttpMethod httpMethod);

}
