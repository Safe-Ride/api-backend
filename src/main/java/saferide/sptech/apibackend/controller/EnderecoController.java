package saferide.sptech.apibackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.EnderecoConstants;
import saferide.sptech.apibackend.dto.endereco.EnderecoMapper;
import saferide.sptech.apibackend.dto.endereco.EnderecoRequest;
import saferide.sptech.apibackend.dto.endereco.EnderecoRequestUpdate;
import saferide.sptech.apibackend.dto.endereco.EnderecoResponse;
import saferide.sptech.apibackend.service.EnderecoService;

import java.util.List;

@RestController
@RequestMapping(EnderecoConstants.BASE_PATH)
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoResponse> criar(
            @Valid @RequestBody EnderecoRequest request) {
        return ResponseEntity.created(null).body(EnderecoMapper.toDto(enderecoService.criar(request)));
    }

    @GetMapping
    public ResponseEntity<List<EnderecoResponse>> listar() {
        return ResponseEntity.ok(EnderecoMapper.toDto(enderecoService.listar()));
    }

    @GetMapping(EnderecoConstants.LIST_BY_ID_PATH)
    public ResponseEntity<EnderecoResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(EnderecoMapper.toDto(enderecoService.listarPorId(id)));
    }

    @PutMapping(EnderecoConstants.UPDATE_PATH)
    public ResponseEntity<EnderecoResponse> atualizar(
            @PathVariable int id,
            @RequestBody EnderecoRequestUpdate request) {
        return ResponseEntity.ok(EnderecoMapper.toDto(enderecoService.atualizar(id, request)));
    }

    @DeleteMapping(EnderecoConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(enderecoService.remover(id));
    }
}
