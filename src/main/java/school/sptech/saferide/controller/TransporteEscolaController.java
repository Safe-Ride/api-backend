package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.transporteEscola.TransporteEscolaMapper;
import school.sptech.saferide.model.entity.transporteEscola.TransporteEscolaRequest;
import school.sptech.saferide.model.entity.transporteEscola.TransporteEscolaResponse;
import school.sptech.saferide.service.TransporteEscolaService;

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
        var payload = TransporteEscolaMapper.toEntity(request);
        var response = service.criar(payload, request.getTransporteId(), request.getEscolaId());
        return ResponseEntity.created(null).body(TransporteEscolaMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem contúdo"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.TRANSPORTE_ESCOLA_LIST_BY_TRANSPORTE_BASE_PATH)
    public ResponseEntity<List<TransporteEscolaResponse>> listarPorTransporte(@PathVariable int transporteId) {
        var response = service.listarPorTransporte(transporteId);
        return ResponseEntity.ok().body(TransporteEscolaMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem contúdo"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.TRANSPORTE_ESCOLA_LIST_BY_ESCOLA_BASE_PATH)
    public ResponseEntity<List<TransporteEscolaResponse>> listarPorEscola(@PathVariable int escolaId) {
        var response = service.listarPorEscola(escolaId);
        return ResponseEntity.ok().body(TransporteEscolaMapper.toDto(response));
    }
}
