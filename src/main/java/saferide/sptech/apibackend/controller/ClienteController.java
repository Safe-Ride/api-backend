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

    @PostMapping("/motoristas")
    public ResponseEntity<Motorista> criarMotorista(@RequestBody Motorista motorista) {
        motorista.setId(++idCliente);
        clientes.add(motorista);
        return ResponseEntity.status(201).body(motorista);
    }

    @PostMapping("/responsaveis")
    public ResponseEntity<Responsavel> criarResponsavel(@RequestBody Responsavel responsavel) {
        responsavel.setId(++idCliente);
        clientes.add(responsavel);
        return ResponseEntity.status(201).body(responsavel);
    }

    @PostMapping("/responsaveis/{idResponsavel}/dependentes")
    public ResponseEntity<Dependente> criarDependente(@PathVariable int idResponsavel, @RequestBody Dependente dependente) {
        Responsavel responsavel = procurarResponsavel(idResponsavel);
        dependente.setId(++idDependente);
        dependente.setIdResponsavel(idResponsavel);
        responsavel.adicionarDependente(dependente);

        return ResponseEntity.status(201).body(dependente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        if (!clientes.isEmpty()){
            ordenarClientePorNome(clientes);
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

    @GetMapping("/responsaveis/{idResponsavel}/dependentes")
    public ResponseEntity<List<Dependente>> listarDependentesPorResponsavel(@PathVariable int idResponsavel) {
        Responsavel responsavel = procurarResponsavel(idResponsavel);

        List<Dependente> dependentes = responsavel.getDependentes();
        if (!dependentes.isEmpty()) {
            ordenarDependenterPorNome(dependentes);
            return ResponseEntity.status(200).body(dependentes);
        }
        return ResponseEntity.status(204).build();
    }
    @GetMapping("/responsaveis/{idResponsavel}/dependentes/{idDependente}")
    public ResponseEntity<Dependente> pegarDependentePorId(@PathVariable int idResponsavel, @PathVariable int idDependente) {
        Responsavel responsavel = procurarResponsavel(idResponsavel);

        Dependente dependente = procurarDependentePorId(idDependente, responsavel.getDependentes());
        if (Objects.nonNull(dependente)) return ResponseEntity.status(200).body(dependente);

        return ResponseEntity.status(404).build();
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

        Cliente cliente = clientes.get(indice);

        // Remover um cliente que tenha dependentes associados
        if (!((Responsavel) cliente).getDependentes().isEmpty()){
            return ResponseEntity.status(400).build();
        }

        clientes.remove(cliente);
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

    private List<Dependente> ordenarDependenterPorNome(List<Dependente> dependentes){
        for (int i = 0; i < dependentes.size(); i++) {
            for (int j = 1; j < dependentes.size(); j++) {

                if (dependentes.get(j).getNome().compareTo(dependentes.get(j-1).getNome()) < 0) {
                    Dependente aux = dependentes.get(j);
                    dependentes.set(j, dependentes.get(j-1));
                    dependentes.set(j-1, aux);

                }
            }
        }
        return dependentes;
    }

    private List<Cliente> ordenarClientePorNome(List<Cliente> clientes){
        for (int i = 0; i < clientes.size() - 1; i++) {
            int indiceMinimo = i;

            for (int j = i + 1; j < clientes.size(); j++) {
                if (clientes.get(j).getNome().compareTo(clientes.get(indiceMinimo).getNome()) < 0) {
                    indiceMinimo = j;
                }
            }
            Cliente aux = clientes.get(indiceMinimo);
            clientes.set(indiceMinimo, clientes.get(i));
            clientes.set(i, aux);
        }
        return clientes;
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
        } catch (ArrayIndexOutOfBoundsException e) {
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
