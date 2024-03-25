package saferide.sptech.apibackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.model.Cliente;
import saferide.sptech.apibackend.model.Dependente;
import saferide.sptech.apibackend.model.Motorista;
import saferide.sptech.apibackend.model.Responsavel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();
    private int idCliente = 0;
    private int idDependente = 0;

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        cliente.setId(++idCliente);
        clientes.add(cliente);
        return ResponseEntity.status(201).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        if (!clientes.isEmpty()){
            return ResponseEntity.status(200).body(clientes);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> pegarClientePorId(@PathVariable int id) {
        int indexCliente = procurarIndexClientePorId(id);

        if (indexCliente > -1) return ResponseEntity.status(200).body(clientes.get(indexCliente));

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Motorista> removerCliente(@PathVariable int id) {
        int indice = procurarIndexClientePorId(id);

        if (indice == -1) return ResponseEntity.status(404).build();

        Cliente cliente = clientes.get(indice);

        // Remover um cliente que tenha dependentes associados
        if (!((Responsavel) cliente).getDependentes().isEmpty()){
            return ResponseEntity.status(400).build();
        }

        clientes.remove(cliente);
        return ResponseEntity.status(204).build();
    }

    private int procurarIndexClientePorId(int id) {
        try {
            return clientes.indexOf(
                    clientes.stream()
                            .filter(c -> c.getId() == id).toList().get(0));
        } catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }
}
