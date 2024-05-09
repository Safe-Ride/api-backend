package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.dependente.DependenteMapper;
import saferide.sptech.apibackend.dto.dependente.DependenteRequest;
import saferide.sptech.apibackend.dto.dependente.DependenteRequestUpdate;
import saferide.sptech.apibackend.dto.dependente.DependenteResponse;
import saferide.sptech.apibackend.service.DependenteService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.DEPENDENTES_BASE_PATH)
@RequiredArgsConstructor
public class DependenteController {

    private final DependenteService service;

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<DependenteResponse> criar(
            @Valid @RequestBody DependenteRequest request) {
        return ResponseEntity.created(null).body(DependenteMapper.toDto(service.criar(request)));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping
    public ResponseEntity<List<DependenteResponse>> listar() {
        return ResponseEntity.ok(DependenteMapper.toDto(service.listar()));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<DependenteResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(DependenteMapper.toDto(service.listarPorId(id)));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PutMapping(ControllerConstants.UPDATE_PATH)
    public ResponseEntity<DependenteResponse> atualizar(
            @PathVariable int id,
            @RequestBody DependenteRequestUpdate request) {
        return ResponseEntity.ok(DependenteMapper.toDto(service.atualizar(id, request)));
    }

    @PatchMapping(ControllerConstants.LINK_DEPENDENTE_WITH_MOTORISTA_PATH)
    public ResponseEntity<DependenteResponse> vincularMotorista(
            @PathVariable int dependenteId,
            @PathVariable int motoristaId) {
        return ResponseEntity.ok().body(DependenteMapper.toDto(service.vincularMotorista(dependenteId, motoristaId)));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @DeleteMapping(ControllerConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(service.remover(id));
    }

}
