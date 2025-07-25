package com.example.demo.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Signo {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String palabra;
    private String definicion;
    private String categoria;
    private String letra;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String urlsJson;

    @JsonProperty
    @Transient
    private List<String> urls;

    private LocalDateTime fechaAlta;

    @PostLoad
    private void loadUrls() {
        try {
            if (urlsJson != null) {
                urls = mapper.readValue(urlsJson, new TypeReference<List<String>>() {});
            } else {
                urls = new ArrayList<>();
            }
        } catch (Exception e) {
            urls = new ArrayList<>();
        }
    }

    @PrePersist
    @PreUpdate
    private void saveUrls() {
        try {
            if (urls != null) {
                urlsJson = mapper.writeValueAsString(urls);
            } else {
                urlsJson = "[]";
            }
        } catch (Exception e) {
            urlsJson = "[]";
        }
    }
}
