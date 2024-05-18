package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.historico.HistoricoMapper;
import saferide.sptech.apibackend.dto.historico.HistoricoRequest;
import saferide.sptech.apibackend.dto.historico.HistoricoResponse;
import saferide.sptech.apibackend.service.HistoricoService;

@RestController
@RequestMapping(ControllerConstants.HISTORICO_BASE_PATH)
@RequiredArgsConstructor
public class HistoricoController {

    private final HistoricoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<HistoricoResponse> criar(
            @Valid @RequestBody HistoricoRequest request) {
        return ResponseEntity.created(null).body(HistoricoMapper.toDto(service.criar(request)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping
    public ResponseEntity<HistoricoResponse> listar(
            @RequestParam int responsavelId,
            @RequestParam int motoristaId) {
        return ResponseEntity.ok(HistoricoMapper.toDto(service.listar(responsavelId, motoristaId)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<HistoricoResponse> listar(
            @PathVariable int id) {
        return ResponseEntity.ok(HistoricoMapper.toDto(service.listarPorId(id)));
    }

}
