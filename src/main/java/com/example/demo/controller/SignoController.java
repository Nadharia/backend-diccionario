package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import com.example.demo.dto.SignoDTO;
import com.example.demo.entity.Signo;
import com.example.demo.services.ISignoService;

@RestController
@CrossOrigin
@RequestMapping("/api/signos")
public class SignoController {

    @Autowired
    private ISignoService service;

    @GetMapping
    public List<Signo> buscar(@RequestParam(required = false) String query) {
        return service.buscarPorQuery(query);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Signo> obtener(@PathVariable Long id) {
        System.out.println("busco signo con un id");
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> guardar(@RequestBody SignoDTO dto) {
        System.out.println("entro y guardo?");
        service.guardar(dto);
        return ResponseEntity.ok(Map.of("message", "Signo guardado con éxito"));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Signo> actualizar(@PathVariable Long id, @RequestBody SignoDTO dto) {
        return service.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
