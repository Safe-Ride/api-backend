package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.contrato.ContratoMapper;
import school.sptech.saferide.model.entity.contrato.ContratoRequest;
import school.sptech.saferide.model.entity.contrato.ContratoResponse;
import school.sptech.saferide.model.entity.contrato.ContratoUpdate;
import school.sptech.saferide.service.ContratoService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.CONTRATO_BASE_PATH)
@RequiredArgsConstructor
public class ContratoController {

    private final ContratoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<ContratoResponse> criar(
            @Valid @RequestBody ContratoRequest request) {
        var payload = ContratoMapper.toEntity(request);
        var response = service.criar(
                payload,
                request.getMotoristaId(),
                request.getResponsavelId(),
                request.getDependentesId());
        return ResponseEntity.created(null).body(ContratoMapper.toDto(response));
    }

    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<ContratoResponse> listarPorId(
            @PathVariable int id) {
        var response = service.listarPorId(id);
        return ResponseEntity.ok().body(ContratoMapper.toDto(response));
    }

    @GetMapping(ControllerConstants.CONTRATO_POR_MOTORISTA_BASE_PATH)
    public ResponseEntity<List<ContratoResponse>> listarPorMotorista(
            @PathVariable int motoristaId) {
        var response = service.listarPorMotorista(motoristaId);
        return ResponseEntity.ok().body(ContratoMapper.toDto(response));
    }

    @GetMapping(ControllerConstants.CONTRATO_POR_RESPONSAVEL_BASE_PATH)
    public ResponseEntity<List<ContratoResponse>> listarPorResponsavel(
            @PathVariable int responsavelId) {
        var response = service.listarPorResponsavel(responsavelId);
        return ResponseEntity.ok().body(ContratoMapper.toDto(response));
    }

    @PutMapping
    public ResponseEntity<ContratoResponse> atualizar(
            @Valid @RequestBody ContratoUpdate request
    ) {
        var response = service.atualizar(request);
        return ResponseEntity.ok(ContratoMapper.toDto(response));
    }

}
