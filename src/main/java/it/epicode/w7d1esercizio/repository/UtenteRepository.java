package it.epicode.w7d1esercizio.repository;

import it.epicode.w7d1esercizio.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    public Optional<Utente> findByUsername(String username);
}
