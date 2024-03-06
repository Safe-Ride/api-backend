package saferide.sptech.apibackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.model.Cliente;
import saferide.sptech.apibackend.model.Dependente;
import saferide.sptech.apibackend.model.Motorista;
import saferide.sptech.apibackend.model.Responsavel;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();

    @PostMapping("/motoristas")
    public ResponseEntity<Motorista> criarMotorista(@RequestBody Motorista motorista) {
        clientes.add(motorista);
        return ResponseEntity.status(201).body(motorista);
    }

    @PostMapping("/responsaveis")
    public ResponseEntity<Responsavel> criarResponsavel(@RequestBody Responsavel responsavel) {
        clientes.add(responsavel);
        return ResponseEntity.status(201).body(responsavel);
    }

    @PostMapping("/responsaveis/{idResponsavel}/dependentes")
    public ResponseEntity<Dependente> criarDependente(@PathVariable int idResponsavel, @RequestBody Dependente dependente) {
        Responsavel responsavel = procurarResponsavel(idResponsavel);

        responsavel.adicionarDependente(dependente);

        return ResponseEntity.status(201).body(dependente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        if (!clientes.isEmpty()) {
            return ResponseEntity.status(200).body(clientes);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/responsaveis/{idResponsavel}/dependentes")
    public ResponseEntity<List<Dependente>> listarDependentesPorResponsavel(@PathVariable int idResponsavel) {
        Responsavel responsavel = procurarResponsavel(idResponsavel);

        List<Dependente> dependentes = responsavel.getDependentes();
        if (!dependentes.isEmpty()) {
            return ResponseEntity.status(200).body(dependentes);
        }
        return ResponseEntity.status(204).build();
    }

    private Responsavel procurarResponsavel(int idResponsavel) {
        return (Responsavel) clientes.stream()
                .filter(c -> c.getClass().equals(Responsavel.class))
                .filter(r -> r.getId() == idResponsavel).toList().get(0);
    }


}
