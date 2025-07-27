package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;
import com.example.demo.services.ISignoService;
import com.example.demo.dto.SignoDTO;
import com.example.demo.entity.Signo;


import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.Usuario;

import com.example.demo.services.IUsuarioService;



@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private IUsuarioService iService;

    

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
        
        return ResponseEntity.ok(iService.register(request));
    }


}
