package saferide.sptech.apibackend.dto.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.DependenteConstants;
import saferide.sptech.apibackend.dto.dependente.DependenteMapper;
import saferide.sptech.apibackend.dto.dependente.DependenteRequest;
import saferide.sptech.apibackend.dto.dependente.DependenteRequestUpdate;
import saferide.sptech.apibackend.dto.dependente.DependenteResponse;
import saferide.sptech.apibackend.service.DependenteService;

import java.util.List;

@RestController
@RequestMapping(DependenteConstants.BASE_PATH)
@RequiredArgsConstructor
public class DependenteController {

    private final DependenteService dependenteService;

    @PostMapping
    public ResponseEntity<DependenteResponse> criar(
            @Valid @RequestBody DependenteRequest request) {
        return ResponseEntity.created(null).body(DependenteMapper.toDto(dependenteService.criar(request)));
    }

    @GetMapping
    public ResponseEntity<List<DependenteResponse>> listar() {
        return ResponseEntity.ok(DependenteMapper.toDto(dependenteService.listar()));
    }

    @GetMapping(DependenteConstants.LIST_BY_ID_PATH)
    public ResponseEntity<DependenteResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(DependenteMapper.toDto(dependenteService.listarPorId(id)));
    }

    @PutMapping(DependenteConstants.UPDATE_PATH)
    public ResponseEntity<DependenteResponse> atualizar(
            @PathVariable int id,
            @RequestBody DependenteRequestUpdate request) {
        return ResponseEntity.ok(DependenteMapper.toDto(dependenteService.atualizar(id, request)));
    }

    @DeleteMapping(DependenteConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(dependenteService.remover(id));
    }
}