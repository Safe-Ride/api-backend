package saferide.sptech.apibackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.model.Cliente;
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
        listarEnderecosOrdenadoPorNome();
        return ResponseEntity.status(200).body(enderecos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        Endereco endereco = idExistnte(id);
        if (endereco == null) return  ResponseEntity.status(400).build();
        enderecos.remove(endereco);
        return ResponseEntity.status(204).build();
    }


    private List<Endereco> listarEnderecosOrdenadoPorNome(){
        int j = 0;
        for (int i = 1; i < enderecos.size(); i++) {
            Endereco endereco = enderecos.get(i);
            j = i-1;
            while (j>=0 && enderecos.get(j).getEndereco().compareTo(endereco.getEndereco())>0){
                enderecos.set(j+1, enderecos.get(j));
                j = j-1;
            }
            enderecos.set(j+1, endereco);
        }

        return enderecos;
    }

    public Endereco idExistnte(int id) {
        return enderecos.stream()
                .filter(e -> e.getIdEndereco() == id)
                .findFirst()
                .orElse(null);
    }
}
