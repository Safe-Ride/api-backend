package saferide.sptech.apibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ClienteConstants;
import saferide.sptech.apibackend.dto.cliente.ClienteRequestUpdateDto;
import saferide.sptech.apibackend.dto.cliente.ClienteRequestDto;
import saferide.sptech.apibackend.dto.cliente.ClienteResponseDto;
import saferide.sptech.apibackend.dto.cliente.ClienteMapper;
import saferide.sptech.apibackend.entity.Cliente;
import saferide.sptech.apibackend.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ClienteConstants.BASE_PATH)
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<ClienteResponseDto> criar(
            @RequestBody ClienteRequestDto clienteCriacao) {
        Cliente entity = ClienteMapper.toEntity(clienteCriacao);
        Cliente saveCliente = clienteRepository.save(entity);
        ClienteResponseDto dto = ClienteMapper.toDto(saveCliente);
        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) return ResponseEntity.status(204).build();
        List<ClienteResponseDto> dtos = ClienteMapper.toDto(clientes);
        return ResponseEntity.status(200).body(dtos);
    }

    @GetMapping(ClienteConstants.ID_PATH)
    public ResponseEntity<ClienteResponseDto> listarPorId(
            @PathVariable int id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isEmpty()) return ResponseEntity.status(404).build();
        ClienteResponseDto dto = ClienteMapper.toDto(clienteOpt.get());
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping(ClienteConstants.ID_PATH)
    public ResponseEntity<ClienteResponseDto> atualizar(
            @PathVariable int id,
            @RequestBody ClienteRequestUpdateDto clienteAtualizacao) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isEmpty()) return ResponseEntity.status(404).build();
        Cliente entity = ClienteMapper.toEntityAtt(clienteAtualizacao,clienteOpt.get());
        Cliente saveCliente = clienteRepository.save(entity);
        ClienteResponseDto dto = ClienteMapper.toDto(saveCliente);
        return ResponseEntity.status(200).body(dto);

    }

    @DeleteMapping(ClienteConstants.ID_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        if (!clienteRepository.existsById(id)) return ResponseEntity.status(400).build();
        clienteRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
