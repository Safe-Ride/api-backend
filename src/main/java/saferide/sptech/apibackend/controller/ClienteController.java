package saferide.sptech.apibackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ClienteConstants;
import saferide.sptech.apibackend.dto.cliente.ClienteRequestUpdateDto;
import saferide.sptech.apibackend.dto.cliente.ClienteRequestDto;
import saferide.sptech.apibackend.dto.cliente.ClienteResponseDto;
import saferide.sptech.apibackend.repository.ClienteRepository;
import saferide.sptech.apibackend.repository.DependenteRepository;
import saferide.sptech.apibackend.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping(ClienteConstants.BASE_PATH)
@RequiredArgsConstructor
public class ClienteController {

    private ClienteRepository clienteRepository;
    private DependenteRepository dependenteRepository;
    ClienteService clienteService = new ClienteService(clienteRepository, dependenteRepository);

    @PostMapping
    public ResponseEntity<ClienteResponseDto> criar(
            @Valid @RequestBody ClienteRequestDto body) {
        return ResponseEntity.status(201).body(clienteService.criar(body));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listar(){
        return ResponseEntity.status(200).body(clienteService.listar());
    }

    @GetMapping(ClienteConstants.LIST_BY_ID_PATH)
    public ResponseEntity<ClienteResponseDto> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(clienteService.listarPorId(id));
    }

    @PutMapping(ClienteConstants.UPDATE_PATH)
    public ResponseEntity<ClienteResponseDto> atualizar(
            @PathVariable int id,
            @RequestBody ClienteRequestUpdateDto request) {
        return ResponseEntity.ok(clienteService.atualizar(id, request));
    }

    @DeleteMapping(ClienteConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(clienteService.remover(id));
    }
}
