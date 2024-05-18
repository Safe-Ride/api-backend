package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.transporteEscola.TransporteEscolaMapper;
import saferide.sptech.apibackend.dto.transporteEscola.TransporteEscolaRequest;
import saferide.sptech.apibackend.dto.transporteEscola.TransporteEscolaResponse;
import saferide.sptech.apibackend.service.TransporteEscolaService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.TRANSPORTE_ESCOLA_BASE_PATH)
@RequiredArgsConstructor
public class TransporteEscolaController {

    private final TransporteEscolaService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<TransporteEscolaResponse> criar(
            @Valid @RequestBody TransporteEscolaRequest request) {
        return ResponseEntity.created(null).body(TransporteEscolaMapper.toDto(service.criar(request)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping
    public ResponseEntity<List<TransporteEscolaResponse>> listar() {
        return ResponseEntity.ok(TransporteEscolaMapper.toDto(service.listar()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<TransporteEscolaResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(TransporteEscolaMapper.toDto(service.listarPorId(id)));
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
