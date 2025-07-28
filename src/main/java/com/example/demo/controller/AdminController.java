package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.Logs;
import com.example.demo.entity.Usuario;

import com.example.demo.services.IUsuarioService;
import com.example.demo.services.LogService;






@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private IUsuarioService iService;
    @Autowired
    private LogService logService;
    

    
    @GetMapping("/obtenerusuarios")
    public ResponseEntity<?> obtenerTodosLosUsuarios() {
        List<Usuario> listaUsuarios = iService.obtenerUsuarios();

        if (listaUsuarios.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(listaUsuarios);
    }

    
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody AuthRequest request) {
        
         iService.register(request);
         return ResponseEntity.ok(Map.of("message", "Usuario registrado"));
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        
        return ResponseEntity.ok().body(iService.deleteUsuario(id));
    }

    @GetMapping("/logs")
    public List<Logs> obtenerLogs() {
        return logService.obtenerLogs();
    }
    

}
