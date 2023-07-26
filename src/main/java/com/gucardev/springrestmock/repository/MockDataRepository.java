package com.gucardev.springrestmock.repository;

import com.gucardev.springrestmock.model.MockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockDataRepository extends JpaRepository<MockData, Long> {}
