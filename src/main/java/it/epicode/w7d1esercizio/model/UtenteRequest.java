package it.epicode.w7d1esercizio.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UtenteRequest {
    @NotBlank(message = "Nome obbligatorio")
    private String nome;
    @NotBlank(message = "Cognome obbligatorio")
    private String cognome;
    @NotBlank(message = "Username obbligatorio")
    private String username;
    @NotBlank(message = "Password obbligatoria")
    private String password;
}
