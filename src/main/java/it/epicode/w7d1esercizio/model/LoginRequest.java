package it.epicode.w7d1esercizio.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Username mancante")
    private String username;
    @NotBlank(message = "Password mancante")
    private String password;
}
