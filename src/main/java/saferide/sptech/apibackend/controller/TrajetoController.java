package saferide.sptech.apibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.model.Trajeto;
import saferide.sptech.apibackend.repositories.TrajetoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trajetos")
public class TrajetoController {
    @Autowired
    private TrajetoRepository trajetoRepository;

    @PostMapping
    public ResponseEntity<Trajeto> criar(@RequestBody Trajeto trajeto){
        Trajeto saveTrajeto = trajetoRepository.save(trajeto);
        return ResponseEntity.status(201).body(saveTrajeto);
    }

    @GetMapping
    public ResponseEntity<List<Trajeto>> listar() {
        List<Trajeto> trajetos = trajetoRepository.findAll();
        if (trajetos.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(trajetos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trajeto> listarPorId(@PathVariable Integer id) {
        Optional<Trajeto> trajetoOpt = trajetoRepository.findById(id);
        return trajetoOpt
                .map(trajeto -> ResponseEntity.status(200).body(trajeto))
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id){
        if (!trajetoRepository.existsById(id)) return ResponseEntity.status(404).build();
        trajetoRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
