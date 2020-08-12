package com.example.httpPoller.repository;

import com.example.httpPoller.model.EndPointStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndPointStatusRepository extends JpaRepository<EndPointStatus, Integer> {

}