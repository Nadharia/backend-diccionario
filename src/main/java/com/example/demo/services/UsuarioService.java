package com.example.demo.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> obtenerUsuarios() {
       List<Usuario> usuarios=usuarioRepository.findAll();
       return usuarios;
        
      
    }

}
