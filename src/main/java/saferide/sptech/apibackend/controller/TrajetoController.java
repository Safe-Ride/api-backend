package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.trajeto.TrajetoMapper;
import saferide.sptech.apibackend.dto.trajeto.TrajetoRequest;
import saferide.sptech.apibackend.dto.trajeto.TrajetoRequestUpdate;
import saferide.sptech.apibackend.dto.trajeto.TrajetoResponse;
import saferide.sptech.apibackend.service.TrajetoService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.TRAJETO_BASE_PATH)
@RequiredArgsConstructor
public class TrajetoController {

    private final TrajetoService service;

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<TrajetoResponse> criar(
            @Valid @RequestBody TrajetoRequest request) {
        return ResponseEntity.created(null).body(TrajetoMapper.toDto(service.criar(request)));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping
    public ResponseEntity<List<TrajetoResponse>> listar() {
        return ResponseEntity.ok(TrajetoMapper.toDto(service.listar()));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<TrajetoResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(TrajetoMapper.toDto(service.listarPorId(id)));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PutMapping(ControllerConstants.UPDATE_PATH)
    public ResponseEntity<TrajetoResponse> atualizar(
            @PathVariable int id,
            @RequestBody TrajetoRequestUpdate request) {
        return ResponseEntity.ok(TrajetoMapper.toDto(service.atualizar(id, request)));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @DeleteMapping(ControllerConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(service.remover(id));
    }

}
