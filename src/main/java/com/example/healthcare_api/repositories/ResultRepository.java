package com.example.healthcare_api.repositories;

import com.example.healthcare_api.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result,Long> {

}
