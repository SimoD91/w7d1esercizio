package it.epicode.w7d1esercizio.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DipendenteRequest {
    @NotNull(message = "Username obbligatorio")
    private String username;
    @NotNull(message = "Nome obbligatorio")
    private String nome;
    @NotNull(message = "Cognome obbligatorio")
    private String cognome;
    @NotNull(message = "Password obbligatoria")
    private String password;

    @Email(message = "Email mancante")
    private String email;

    private List<Dispositivo> dispositivi;
}
