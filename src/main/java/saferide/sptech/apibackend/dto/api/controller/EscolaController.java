package saferide.sptech.apibackend.dto.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.EscolaConstants;
import saferide.sptech.apibackend.constants.TrajetoConstants;
import saferide.sptech.apibackend.dto.escola.EscolaMapper;
import saferide.sptech.apibackend.dto.escola.EscolaRequest;
import saferide.sptech.apibackend.dto.escola.EscolaRequestUpdate;
import saferide.sptech.apibackend.dto.escola.EscolaResponse;
import saferide.sptech.apibackend.service.EscolaService;

import java.util.List;

@RestController
@RequestMapping(EscolaConstants.BASE_PATH)
@RequiredArgsConstructor
public class EscolaController {

    private final EscolaService escolaService;
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<EscolaResponse> criar(
            @Valid @RequestBody EscolaRequest request) {
        return ResponseEntity.created(null).body(EscolaMapper.toDto(escolaService.criar(request)));
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<EscolaResponse>> listar() {
        return ResponseEntity.ok(EscolaMapper.toDto(escolaService.listar()));
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping(TrajetoConstants.LIST_BY_ID_PATH)
    public ResponseEntity<EscolaResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(EscolaMapper.toDto(escolaService.listarPorId(id)));
    }
    @SecurityRequirement(name = "Bearer")
    @PutMapping(TrajetoConstants.UPDATE_PATH)
    public ResponseEntity<EscolaResponse> atualizar(
            @PathVariable int id,
            @RequestBody EscolaRequestUpdate request) {
        return ResponseEntity.ok(EscolaMapper.toDto(escolaService.atualizar(id, request)));
    }
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping(TrajetoConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(escolaService.remover(id));
    }
}
