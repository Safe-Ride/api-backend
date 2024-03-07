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
        dependente.setIdResponsavel(idResponsavel);
        responsavel.adicionarDependente(dependente);

        return ResponseEntity.status(201).body(dependente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        if (!clientes.isEmpty()) return ResponseEntity.status(200).body(clientes);

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

    @PutMapping("/motoristas/{idMotorista}")
    public ResponseEntity<Motorista> atualizarMotorista(@PathVariable int idMotorista, @RequestBody Motorista motorista) {
        int motoristaIndex = procurarIndexClientePorId(idMotorista);

        if (motoristaIndex == -1) return ResponseEntity.status(404).build();

        motorista.setId(clientes.get(motoristaIndex).getId());
        clientes.set(motoristaIndex, motorista);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/responsaveis/{idResponsavel}")
    public ResponseEntity<Responsavel> atualizarResponsavel(@PathVariable int idResponsavel, @RequestBody Responsavel responsavel) {
        int responsavelIndex = procurarIndexClientePorId(idResponsavel);
        if (responsavelIndex == -1) return ResponseEntity.status(404).build();

        Responsavel responsavelAntigo = (Responsavel) clientes.get(responsavelIndex);
        responsavel.setId(idResponsavel);
        for (Dependente dependente : responsavelAntigo.getDependentes()) {
            responsavel.adicionarDependente(dependente);
        }
        clientes.set(responsavelIndex, responsavel);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/responsaveis/{idResponsavel}/dependentes/{idDependente}")
    public ResponseEntity<Dependente> atualizarDependente(@PathVariable int idResponsavel, @PathVariable int idDependente, @RequestBody Dependente dependente) {
        Responsavel responsavel = procurarResponsavel(idResponsavel);
        List<Dependente> dependentes = responsavel.getDependentes();
        Dependente dependenteAntigo = procurarDependentePorId(idDependente, dependentes);

        if (Objects.isNull(dependenteAntigo)) {
            return ResponseEntity.status(404).build();
        }

        int indiceDependente = dependentes.indexOf(dependenteAntigo);
        dependente.setId(idDependente);
        dependente.setIdResponsavel(idResponsavel);

        dependentes.set(indiceDependente, dependente);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Motorista> removerCliente(@PathVariable int id) {
        int indice = procurarIndexClientePorId(id);

        if (indice == -1) return ResponseEntity.status(404).build();

        clientes.remove(indice);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/responsaveis/{idResponsavel}/dependentes/{idDependente}")
    public ResponseEntity<Dependente> removerDependente(@PathVariable int idResponsavel, @PathVariable int idDependente) {
        List<Dependente> dependentes = procurarResponsavel(idResponsavel).getDependentes();
        Dependente dependente = procurarDependentePorId(idDependente, dependentes);
        if (Objects.isNull(dependente)) return ResponseEntity.status(404).build();
        dependentes.remove(dependente);
        return ResponseEntity.status(204).build();
    }

    private Responsavel procurarResponsavel(int idResponsavel) {
        return (Responsavel) clientes.stream()
                .filter(c -> c.getClass().equals(Responsavel.class))
                .filter(r -> r.getId() == idResponsavel).toList().get(0);
    }

    private int procurarIndexClientePorId(int id) {
        try {
            return clientes.indexOf(
                    clientes.stream()
                            .filter(c -> c.getId() == id).toList().get(0));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    private Dependente procurarDependentePorId(int idDependente, List<Dependente> dependentes) {
        try {
            return dependentes
                    .stream()
                    .filter(d -> d.getId() == idDependente)
                    .toList().get(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
