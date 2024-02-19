package it.epicode.w7d1esercizio.controller;

import it.epicode.w7d1esercizio.exception.BadRequestException;
import it.epicode.w7d1esercizio.model.Dispositivo;
import it.epicode.w7d1esercizio.model.DispositivoRequest;
import it.epicode.w7d1esercizio.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;
    @GetMapping("/dispositivi")
    public Page<Dispositivo> getAll(Pageable pageable){
        return dispositivoService.getAllDispositivi(pageable);
    }
    @GetMapping("/dispositivi/{id}")
    public Dispositivo getDispositivoById(@PathVariable int id){
        return dispositivoService.getDispositivoId(id);
    }
    @PostMapping("/dispositivi")
    public Dispositivo saveDispositivo(@RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return dispositivoService.saveDispositivo(dispositivoRequest);
    }
    @DeleteMapping("/dispositivi/{id}")
    public void deleteDispositivi(@PathVariable int id){
        dispositivoService.deleteDispositivo(id);
    }
    @PutMapping("/dispositivi/{id}")
    public Dispositivo updateDispositivo(@PathVariable int id, @RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        } return dispositivoService.updateDispositivo(id,dispositivoRequest);
    }
}
