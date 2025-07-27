package com.example.demo.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.*;

import org.springframework.web.bind.annotation.*;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.enums.Rol;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.services.IUsuarioService;






@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private  AuthenticationManager authenticationManager;
     @Autowired
    private  JwtUtil jwtUtil;
    @Autowired
    private IUsuarioService iService;
    @Autowired
    private UsuarioRepository usuarioRepository;

   

    

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String token = jwtUtil.generateToken(request.getUsername());

        Cookie cookie = jwtUtil.createJwtCookie(token);
        response.addCookie(cookie);

        Rol rol=iService.findByUser(request.getUsername());

        return ResponseEntity.ok(Map.of("message", "Login exitoso","rol",rol.name()));
    }


    

    
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok("Sesión cerrada");
    }


    @PostMapping("/TEST")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        
        return ResponseEntity.ok(iService.register(request));
    }
}
