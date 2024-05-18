package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.escola.EscolaMapper;
import saferide.sptech.apibackend.dto.escola.EscolaRequest;
import saferide.sptech.apibackend.dto.escola.EscolaRequestUpdate;
import saferide.sptech.apibackend.dto.escola.EscolaResponse;
import saferide.sptech.apibackend.service.EscolaService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.ESCOLAS_BASE_PATH)
@RequiredArgsConstructor
public class EscolaController {

    private final EscolaService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<EscolaResponse> criar(
            @Valid @RequestBody EscolaRequest request) {
        return ResponseEntity.created(null).body(EscolaMapper.toDto(service.criar(request)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping
    public ResponseEntity<List<EscolaResponse>> listar() {
        return ResponseEntity.ok(EscolaMapper.toDto(service.listar()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<EscolaResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(EscolaMapper.toDto(service.listarPorId(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PutMapping(ControllerConstants.UPDATE_PATH)
    public ResponseEntity<EscolaResponse> atualizar(
            @PathVariable int id,
            @RequestBody EscolaRequestUpdate request) {
        return ResponseEntity.ok(EscolaMapper.toDto(service.atualizar(id, request)));
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
