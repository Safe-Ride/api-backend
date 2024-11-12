package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.transporte.TransporteMapper;
import school.sptech.saferide.model.entity.transporte.TransporteRequest;
import school.sptech.saferide.model.entity.transporte.TransporteResponse;
import school.sptech.saferide.service.TransporteService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.TRANSPORTE_BASE_PATH)
@RequiredArgsConstructor
public class TransporteController {

    private final TransporteService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @PostMapping
    public ResponseEntity<TransporteResponse> criar(
            @Valid @RequestBody TransporteRequest request) {
        var payload = TransporteMapper.toEntity(request);
        var response = service.criar(
                payload,
                request.getUsuarioId());
        return ResponseEntity.created(null).body(TransporteMapper.toDto(response));
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
        var response = service.listarPorId(id);
        return ResponseEntity.ok(TransporteMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.TRANSPORTE_POR_MOTORISTA_BASE_PATH)
    public ResponseEntity<List<TransporteResponse>> listarPorMotoristaId(
            @PathVariable int motoristaId) {
        var response = service.listarPorMotoristaId(motoristaId);
        return ResponseEntity.ok(TransporteMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removido"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @DeleteMapping(ControllerConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        var response = service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
