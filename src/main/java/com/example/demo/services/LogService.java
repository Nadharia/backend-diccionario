package com.example.demo.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Logs;
import com.example.demo.repository.LogRepository;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

   public List<Logs> obtenerLogs() {
        return logRepository.findAll();
    }

    
}
