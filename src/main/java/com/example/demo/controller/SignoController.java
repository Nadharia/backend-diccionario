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
import org.springframework.http.HttpStatus;
import java.util.HashMap;
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
    public ResponseEntity<Map<String, Object>> guardar(@RequestBody SignoDTO dto) {
        return service.guardar(dto).map(signoGuardado -> {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Guardado con éxito");
            respuesta.put("signo", signoGuardado); 
            return ResponseEntity.ok(respuesta);
        }).orElseGet(() -> {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "Error al guardar el signo");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        });
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable Long id, @RequestBody SignoDTO dto) {
        System.out.println("entro y actualizo?");
        return service.actualizar(id, dto).map(signoActualizado -> {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Actualizado con éxito");
            respuesta.put("signo", signoActualizado);
            return ResponseEntity.ok(respuesta);
        }).orElseGet(() -> {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "No se encontró el signo");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        });
}

}
