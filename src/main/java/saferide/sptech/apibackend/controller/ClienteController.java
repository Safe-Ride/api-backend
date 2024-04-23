package saferide.sptech.apibackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ClienteConstants;
import saferide.sptech.apibackend.dto.cliente.ClienteRequestUpdate;
import saferide.sptech.apibackend.dto.cliente.ClienteRequest;
import saferide.sptech.apibackend.dto.cliente.ClienteResponse;
import saferide.sptech.apibackend.repository.ClienteRepository;
import saferide.sptech.apibackend.repository.DependenteRepository;
import saferide.sptech.apibackend.repository.EnderecoRepository;
import saferide.sptech.apibackend.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping(ClienteConstants.BASE_PATH)
@RequiredArgsConstructor
public class ClienteController {

    private ClienteRepository clienteRepository;
    private DependenteRepository dependenteRepository;
    private EnderecoRepository enderecoRepository;
    ClienteService clienteService = new ClienteService(clienteRepository, dependenteRepository, enderecoRepository);

    @PostMapping
    public ResponseEntity<ClienteResponse> criar(
            @Valid @RequestBody ClienteRequest body) {
        return ResponseEntity.created(null).body(clienteService.criar(body));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar(){
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping(ClienteConstants.LIST_BY_ID_PATH)
    public ResponseEntity<ClienteResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(clienteService.listarPorId(id));
    }

    @PutMapping(ClienteConstants.UPDATE_PATH)
    public ResponseEntity<ClienteResponse> atualizar(
            @PathVariable int id,
            @RequestBody ClienteRequestUpdate request) {
        return ResponseEntity.ok(clienteService.atualizar(id, request));
    }

    @PatchMapping(ClienteConstants.UPDATE_ENDERECO_PATH)
    public ResponseEntity<ClienteResponse> atualizar(
            @PathVariable int id,
            @RequestParam int endereco_id) {
        return ResponseEntity.ok(clienteService.atualizarEndereco(id, endereco_id));
    }

    @DeleteMapping(ClienteConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(clienteService.remover(id));
    }
}
