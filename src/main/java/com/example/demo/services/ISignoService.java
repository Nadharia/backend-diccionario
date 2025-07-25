package com.example.demo.services;

import java.util.List;
import java.util.Optional;


import com.example.demo.entity.Signo;

import com.example.demo.dto.SignoDTO;

public interface ISignoService {
   
    Optional<Signo> buscarPorId(Long id);
    String  guardar(SignoDTO dto);
    List<Signo> buscarPorQuery(String query);
}
