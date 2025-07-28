package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.Logs;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.enums.Rol;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.LogRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> obtenerUsuarios() {
        crearLog("OBTENER_USUARIOS", "Se solicitó la lista de usuarios");
        return usuarioRepository.findAll();
    }

    @Override
    public String register(AuthRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(request.getUsername());
        if (usuarioOpt.isPresent()) {
            throw new UserAlreadyExistsException("El usuario ya existe");
        }
        Optional<Usuario> emailOpt = usuarioRepository.findByEmail(request.getEmail());
        if (emailOpt.isPresent()) {
            throw new UserAlreadyExistsException("El email ya está registrado");
        }

        Usuario nuevo = new Usuario();
        nuevo.setEmail(request.getEmail());
        nuevo.setUsername(request.getUsername());
        nuevo.setPassword(passwordEncoder.encode(request.getPassword()));
        nuevo.setRol(Rol.USER);
        usuarioRepository.save(nuevo);

        crearLog("REGISTRO_USUARIO", "Usuario registrado: " + request.getUsername());

        return "Usuario registrado";
    }

    @Override
    public String deleteUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);

            crearLog("ELIMINAR_USUARIO", "Usuario eliminado con ID: " + id);

            return "Usuario eliminado";
        } else {
            throw new UserNotFoundException("Usuario no encontrado");
        }
    }

    @Override
    public Rol findByUser(String username) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isEmpty()) {
            throw new UserNotFoundException("El usuario no existe");
        }

        crearLog("CONSULTAR_ROL", "Se consultó el rol del usuario: " + username);

        return usuarioOpt.get().getRol();
    }

    public void crearLog(String accion, String descripcion) {
        Logs log = new Logs();

        log.setDescripcion(descripcion);
        log.setFecha(LocalDateTime.now());

        Usuario currentUser = getCurrentUser();
        if (currentUser != null) {
            log.setUsuario(currentUser);
        }

        logRepository.save(log);
    }

    public Usuario getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return null; // no hay usuario autenticado
        }
        String username = auth.getName();
        return usuarioRepository.findByUsername(username).orElse(null);
    }
}
