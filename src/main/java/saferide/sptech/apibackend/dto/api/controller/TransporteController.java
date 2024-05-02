package saferide.sptech.apibackend.dto.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.TrajetoConstants;
import saferide.sptech.apibackend.constants.TransporteConstants;
import saferide.sptech.apibackend.dto.transporte.TransporteMapper;
import saferide.sptech.apibackend.dto.transporte.TransporteRequest;
import saferide.sptech.apibackend.dto.transporte.TransporteRequestUpdate;
import saferide.sptech.apibackend.dto.transporte.TransporteResponse;
import saferide.sptech.apibackend.service.TransporteService;

import java.util.List;

@RestController
@RequestMapping(TransporteConstants.BASE_PATH)
@RequiredArgsConstructor
public class TransporteController {

    private final TransporteService transporteService;
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<TransporteResponse> criar(
            @Valid @RequestBody TransporteRequest request) {
        return ResponseEntity.created(null).body(TransporteMapper.toDto(transporteService.criar(request)));
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<TransporteResponse>> listar() {
        return ResponseEntity.ok(TransporteMapper.toDto(transporteService.listar()));
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping(TrajetoConstants.LIST_BY_ID_PATH)
    public ResponseEntity<TransporteResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(TransporteMapper.toDto(transporteService.listarPorId(id)));
    }
    @SecurityRequirement(name = "Bearer")
    @PutMapping(TrajetoConstants.UPDATE_PATH)
    public ResponseEntity<TransporteResponse> atualizar(
            @PathVariable int id,
            @RequestBody TransporteRequestUpdate request) {
        return ResponseEntity.ok(TransporteMapper.toDto(transporteService.atualizar(id, request)));
    }
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping(TrajetoConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(transporteService.remover(id));
    }
}
