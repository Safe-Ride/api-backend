package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.historico.HistoricoMapper;
import school.sptech.saferide.model.entity.historico.HistoricoRequest;
import school.sptech.saferide.model.entity.historico.HistoricoResponse;
import school.sptech.saferide.service.HistoricoService;

import java.util.List;

@Controller
@RequestMapping(ControllerConstants.HISTORICO_BASE_PATH)
@RequiredArgsConstructor
public class HistoricoController {

    private final HistoricoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<HistoricoResponse> criar(
            @Valid @RequestBody HistoricoRequest request) {
        var payload = HistoricoMapper.toEntity(request);
        var response = service.criar(payload, request.getTrajetoId());
        return ResponseEntity.created(null).body(HistoricoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<HistoricoResponse> listarPorId(
            @PathVariable int id) {
        var response = service.listarPorId(id);
        return ResponseEntity.ok(HistoricoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping()
    public ResponseEntity<List<HistoricoResponse>> listar() {
        var response = service.listar();
        if (response.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(HistoricoMapper.toDto(response));
    }
}
