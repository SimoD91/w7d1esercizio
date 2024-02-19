package it.epicode.w7d1esercizio.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class DipendenteRequest {
    @NotBlank(message = "Username mancante")
    private String username;

    @NotBlank(message = "Nome mancante")
    private String nome;

    @NotBlank(message = "Cognome mancante")
    private String cognome;

    @NotBlank(message = "Password mancante")
    private String password;

    @Email(message = "Email mancante")
    private String email;

    private List<Dispositivo> dispositivi;
}
