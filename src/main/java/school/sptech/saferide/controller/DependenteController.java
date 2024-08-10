package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.dependente.DependenteMapper;
import school.sptech.saferide.model.entity.dependente.DependenteRequest;
import school.sptech.saferide.model.entity.dependente.DependenteResponse;
import school.sptech.saferide.service.DependenteService;

@RestController
@RequestMapping(ControllerConstants.DEPENDENTES_BASE_PATH)
@RequiredArgsConstructor
public class DependenteController {

    private final DependenteService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @PostMapping
    public ResponseEntity<DependenteResponse> criar(
            @Valid @RequestBody DependenteRequest request) {
        var payload = DependenteMapper.toEntity(request);
        var response = service.criar(
                payload,
                request.getResponsavelId(),
                request.getEscolaId());
        return ResponseEntity.created(null).body(DependenteMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<DependenteResponse> listarPorId(
            @PathVariable int id) {
        var response = service.listarPorId(id);
        return ResponseEntity.ok(DependenteMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @PatchMapping(ControllerConstants.DEPENDENTE_LINK_MOTORISTA_PATH)
    public ResponseEntity<DependenteResponse> vincularMotorista(
            @PathVariable int dependenteId,
            @PathVariable int motoristaId) {
        var response = service.vincularMotorista(dependenteId, motoristaId);
        return ResponseEntity.ok().body(DependenteMapper.toDto(response));
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
        var response = service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
