package it.epicode.w7d1esercizio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome;
    private String cognome;
    private String username;
    private String password;
}
