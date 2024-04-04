package com.example.healthcare_api.repositories;

import com.example.healthcare_api.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test,Long> {
}
