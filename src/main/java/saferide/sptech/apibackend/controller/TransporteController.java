package saferide.sptech.apibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.model.Trajeto;
import saferide.sptech.apibackend.model.Transporte;
import saferide.sptech.apibackend.repositories.TransporteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transportes")
public class TransporteController {
    @Autowired
    private TransporteRepository transporteRepository;

    @PostMapping
    public ResponseEntity<Transporte> criar(@RequestBody Transporte transporte){
        Transporte saveTransporte = transporteRepository.save(transporte);
        return ResponseEntity.status(201).body(saveTransporte);
    }

    @GetMapping
    public ResponseEntity<List<Transporte>> listar() {
        List<Transporte> transportes = transporteRepository.findAll();
        if (transportes.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(transportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transporte> listarPorId(@PathVariable Integer id) {
        Optional<Transporte> transporteOpt = transporteRepository.findById(id);
        return transporteOpt
                .map(transporte -> ResponseEntity.status(200).body(transporte))
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id){
        if (!transporteRepository.existsById(id)) return ResponseEntity.status(404).build();
        transporteRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }


}
