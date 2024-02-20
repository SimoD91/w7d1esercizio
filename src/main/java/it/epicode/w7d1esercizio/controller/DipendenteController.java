package it.epicode.w7d1esercizio.controller;

import it.epicode.w7d1esercizio.exception.BadRequestException;
import it.epicode.w7d1esercizio.model.Dipendente;
import it.epicode.w7d1esercizio.model.DipendenteRequest;
import it.epicode.w7d1esercizio.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class DipendenteController {
    @Autowired
    DipendenteService dipendenteService;

    @GetMapping("/dipendenti")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Dipendente> getAlL(Pageable pageable){
        return dipendenteService.getAllDipendenti(pageable);
    }
    @GetMapping("/dipendenti/{id}")
    public Dipendente getDipendenteById(@PathVariable int id){
        return dipendenteService.getDipendenteId(id);
    }

    @PostMapping("/dipendenti")
    public Dipendente saveDipendente(@RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return dipendenteService.saveDipendente(dipendenteRequest);
    }
    @DeleteMapping("/dipendenti/{id}")
    public void deleteDipendente(@PathVariable int id){
        dipendenteService.deleteDipendente(id);
    }
    @PutMapping("/dipendenti/{id}")
    public Dipendente updateDipendente(@PathVariable int id, @RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return dipendenteService.updateDipendente(id, dipendenteRequest);
    }
}
