package com.example.demo.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.enums.Rol;
import com.example.demo.repository.UsuarioRepository;
@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<Usuario> obtenerUsuarios() {
       List<Usuario> usuarios=usuarioRepository.findAll();
       return usuarios;
        
      
    }
    @Override
    public String register(AuthRequest request) {
        Usuario nuevo = new Usuario();
        nuevo.setEmail(request.getEmail());
        nuevo.setUsername(request.getUsername());
        nuevo.setPassword(passwordEncoder.encode(request.getPassword()));
        nuevo.setRol(Rol.ADMIN);
        usuarioRepository.save(nuevo);
        return "Usuario registrado";
    }
    @Override
    public String deleteUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Usuario eliminado";
        } else {
            return "Usuario no encontrado";
        }
    }
    

}
