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
public String guardar(SignoDTO s) {
    repository.save(dtoToEntity(s));
    return "Guardado con éxito";
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

    
}


    
    
