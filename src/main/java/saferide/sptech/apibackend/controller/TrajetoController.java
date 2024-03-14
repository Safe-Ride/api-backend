package saferide.sptech.apibackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.model.Endereco;
import saferide.sptech.apibackend.model.Responsavel;
import saferide.sptech.apibackend.model.Trajeto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trajetos")
public class TrajetoController {
    private List<Trajeto> trajetos = new ArrayList<>();
    private int idTrajeto = 0;

    @PostMapping
    public ResponseEntity<Trajeto> cadastrar(@RequestBody Trajeto trajeto){
        if (trajeto.getEnderecos().isEmpty()) return ResponseEntity.status(400).build();
        trajeto.setIdTrajeto(++idTrajeto);
        trajetos.add(trajeto);
        return ResponseEntity.status(201).body(trajeto);
    }

    @GetMapping
    public ResponseEntity<List<Trajeto>> listar() {
        if (trajetos.isEmpty()) return ResponseEntity.status(200).build();
        return ResponseEntity.status(200).body(trajetos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trajeto> listarPorId(@PathVariable int id) {
        Trajeto trajeto = idExistente(id);
        if (trajeto == null) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(trajeto);
    }

    public Trajeto idExistente(int id){
        return trajetos.stream()
                .filter(t -> t.getIdTrajeto() == id)
                .findFirst()
                .orElse(null);
    }
}
