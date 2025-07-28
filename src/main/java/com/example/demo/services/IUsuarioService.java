package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.enums.Rol;

public interface IUsuarioService {

    List<Usuario> obtenerUsuarios(); // Usá camelCase con mayúscula intermedia

    String register(AuthRequest request);

    String deleteUsuario(Long id);

    Rol findByUser(String username);
}
