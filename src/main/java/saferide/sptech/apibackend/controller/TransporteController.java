package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.transporte.TransporteMapper;
import saferide.sptech.apibackend.dto.transporte.TransporteRequest;
import saferide.sptech.apibackend.dto.transporte.TransporteRequestUpdate;
import saferide.sptech.apibackend.dto.transporte.TransporteResponse;
import saferide.sptech.apibackend.service.TransporteService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.TRANSPORTE_BASE_PATH)
@RequiredArgsConstructor
public class TransporteController {

    private final TransporteService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<TransporteResponse> criar(
            @Valid @RequestBody TransporteRequest request) {
        return ResponseEntity.created(null).body(TransporteMapper.toDto(service.criar(request)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping
    public ResponseEntity<List<TransporteResponse>> listar() {
        return ResponseEntity.ok(TransporteMapper.toDto(service.listar()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<TransporteResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(TransporteMapper.toDto(service.listarPorId(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PutMapping(ControllerConstants.UPDATE_PATH)
    public ResponseEntity<TransporteResponse> atualizar(
            @PathVariable int id,
            @RequestBody TransporteRequestUpdate request) {
        return ResponseEntity.ok(TransporteMapper.toDto(service.atualizar(id, request)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @DeleteMapping(ControllerConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(service.remover(id));
    }

}
