package saferide.sptech.apibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.dto.cliente.ClienteAtualizacaoDto;
import saferide.sptech.apibackend.dto.cliente.ClienteCriacaoDto;
import saferide.sptech.apibackend.dto.cliente.ClienteListagemDto;
import saferide.sptech.apibackend.dto.cliente.ClienteMapper;
import saferide.sptech.apibackend.entity.Cliente;
import saferide.sptech.apibackend.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<ClienteListagemDto> criar(
            @RequestBody ClienteCriacaoDto clienteCriacao) {
        Cliente entity = ClienteMapper.toEntity(clienteCriacao);
        Cliente saveCliente = clienteRepository.save(entity);
        ClienteListagemDto dto = ClienteMapper.toDto(saveCliente);
        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteListagemDto>> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) return ResponseEntity.status(204).build();
        List<ClienteListagemDto> dtos = ClienteMapper.toDto(clientes);
        return ResponseEntity.status(200).body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteListagemDto> listarPorId(
            @PathVariable int id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isEmpty()) return ResponseEntity.status(404).build();
        ClienteListagemDto dto = ClienteMapper.toDto(clienteOpt.get());
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteListagemDto> atualizar(
            @PathVariable int id,
            @RequestBody ClienteAtualizacaoDto clienteAtualizacao) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isEmpty()) return ResponseEntity.status(404).build();
        Cliente entity = ClienteMapper.toEntityAtt(clienteAtualizacao,clienteOpt.get());
        Cliente saveCliente = clienteRepository.save(entity);
        ClienteListagemDto dto = ClienteMapper.toDto(saveCliente);
        return ResponseEntity.status(200).body(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        if (!clienteRepository.existsById(id)) return ResponseEntity.status(400).build();
        clienteRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
