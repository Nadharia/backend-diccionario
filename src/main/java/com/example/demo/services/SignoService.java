package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SignoDTO;
import com.example.demo.entity.Signo;
import com.example.demo.repository.SignoRepository;

@Service
public class SignoService implements ISignoService {

    @Autowired
    private SignoRepository repository;

   

    @Override
    public Optional<Signo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
public Optional<Signo> guardar(SignoDTO dto) {
    try {
        Signo signo = repository.save(dtoToEntity(dto));
        return Optional.of(signo); 
    } catch (Exception e) {
        return Optional.empty();
    }
}

public void eliminar(Long id) {
    repository.deleteById(id);
}

public Signo dtoToEntity(SignoDTO s) {
    Signo signo = new Signo();
    signo.setPalabra(s.getPalabra());
    signo.setCategoria(s.getCategoria());
    signo.setDefinicion(s.getDefinicion());
    signo.setFechaAlta(LocalDateTime.now());
    signo.setLetra(s.getLetra());
    signo.setUrls(s.getUrls());
    return signo;
}



@Override
public List<Signo> buscarPorQuery(String query) {
    
    if (query != null && !query.isEmpty()) {
        return repository.findByPalabraContainingIgnoreCase(query);
    }
    return repository.findAll();
}

@Override
public Optional<Signo> actualizar(Long id, SignoDTO dto) {
    Signo signoExistente = repository.findById(id).orElse(null);
    if (signoExistente != null) {
        signoExistente.setPalabra(dto.getPalabra());
        signoExistente.setCategoria(dto.getCategoria());
        signoExistente.setDefinicion(dto.getDefinicion());
        signoExistente.setLetra(dto.getLetra());
        signoExistente.setUrls(dto.getUrls());
        repository.save(signoExistente);
        return Optional.of(signoExistente);
    }
    return Optional.empty();
}
    
}


    
    
