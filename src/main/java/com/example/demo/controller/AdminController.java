package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.enums.Rol;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.services.IUsuarioService;



@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private IUsuarioService iService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtenerusuarios")
    public ResponseEntity<?> obtenerTodosLosUsuarios() {
        List<Usuario> listaUsuarios = iService.obtenerUsuarios();

        if (listaUsuarios.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(listaUsuarios);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        Usuario nuevo = new Usuario();
        nuevo.setEmail(request.getEmail());
        nuevo.setUsername(request.getUsername());
        nuevo.setPassword(passwordEncoder.encode(request.getPassword()));
        nuevo.setRol(Rol.USER);
        usuarioRepository.save(nuevo);
        return ResponseEntity.ok("Usuario registrado");
    }

}
