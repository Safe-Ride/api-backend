package saferide.sptech.apibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.entity.Endereco;
import saferide.sptech.apibackend.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping
    public ResponseEntity<Endereco> cadastrar(@RequestBody Endereco endereco){
//        endereco.setIdEndereco(null);
        Endereco saveEndereco = enderecoRepository.save(endereco);
        return ResponseEntity.status(201).body(saveEndereco);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listar() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        if (enderecos.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> listarPorId(@PathVariable Integer id) {
        Optional<Endereco> clienteOpt = enderecoRepository.findById(id);
        return clienteOpt
                .map(endereco -> ResponseEntity.status(200).body(endereco))
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Endereco> remover(@PathVariable int id){
        if (!enderecoRepository.existsById(id)) return ResponseEntity.status(400).build();
        enderecoRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
