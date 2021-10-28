package com.example.dmaker.repository;

import com.example.dmaker.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

}
