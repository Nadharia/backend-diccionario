package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Logs;

public interface LogRepository extends JpaRepository<Logs, Long> {
    

    
} 


