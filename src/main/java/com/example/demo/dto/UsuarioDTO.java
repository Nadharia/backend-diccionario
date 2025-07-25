package com.example.demo.dto;




import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

   
    @NotBlank(message = "El usuario es obligatorio")
    private String Usuario;

   

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
    
}
