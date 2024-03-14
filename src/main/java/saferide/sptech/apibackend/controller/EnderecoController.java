package saferide.sptech.apibackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.model.Endereco;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private List<Endereco> enderecos = new ArrayList<>();
    private int idEndereco = 0;

    @PostMapping
    public ResponseEntity<Endereco> cadastrar(@RequestBody Endereco endereco){
        endereco.setIdEndereco(++idEndereco);
        enderecos.add(endereco);
        return ResponseEntity.status(201).body(endereco);
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Endereco>> cadastrarLista(@RequestBody List<Endereco> listaEnderecos){
        for (Endereco endereco:listaEnderecos) {
            endereco.setIdEndereco(++idEndereco);
            enderecos.add(endereco);
        }
        return ResponseEntity.status(201).body(enderecos);
    }
    @GetMapping
    public ResponseEntity<List<Endereco>> listar() {
        if (enderecos.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(enderecos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        Endereco endereco = idExistnte(id);
        if (endereco == null) return  ResponseEntity.status(400).build();
        enderecos.remove(endereco);
        return ResponseEntity.status(204).build();
    }

    public Endereco idExistnte(int id) {
        return enderecos.stream()
                .filter(e -> e.getIdEndereco() == id)
                .findFirst()
                .orElse(null);
    }
}
