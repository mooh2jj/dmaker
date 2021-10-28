package com.example.dmaker.repository;

import com.example.dmaker.entity.Developer;
import com.example.dmaker.entity.RetiredDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RetiredDeveloperRepository extends JpaRepository<RetiredDeveloper, Long> {

}
