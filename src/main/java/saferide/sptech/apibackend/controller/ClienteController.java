package saferide.sptech.apibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.model.Cliente;
import saferide.sptech.apibackend.repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
//        cliente.setId(null);
        Cliente saveCliente = clienteRepository.save(cliente);
        return ResponseEntity.status(201).body(saveCliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarPorId(@PathVariable Integer id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        return clienteOpt
                .map(cliente -> ResponseEntity.status(200).body(cliente))
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> remover(@PathVariable Integer id) {
        if (!clienteRepository.existsById(id)) return ResponseEntity.status(400).build();
        clienteRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
