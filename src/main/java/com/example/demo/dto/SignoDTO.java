package com.example.demo.dto;

import java.util.List;


import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class SignoDTO {

    private String palabra;
    
    private String definicion;
    private String categoria;
    private String letra;
    @Nullable
    private List<String> urls;



    
   
    
    
}
