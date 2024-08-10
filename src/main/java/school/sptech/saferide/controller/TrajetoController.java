package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.trajeto.TrajetoMapper;
import school.sptech.saferide.model.entity.trajeto.TrajetoRequest;
import school.sptech.saferide.model.entity.trajeto.TrajetoResponse;
import school.sptech.saferide.service.TrajetoService;

@RestController
@RequestMapping(ControllerConstants.TRAJETO_BASE_PATH)
@RequiredArgsConstructor
public class TrajetoController {

    private final TrajetoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<TrajetoResponse> criar(
            @Valid @RequestBody TrajetoRequest request) {
        var payload = TrajetoMapper.toEntity(request);
        var response = service.criar(
                payload,
                request.getMotoristaId(),
                request.getEscolaId());
        return ResponseEntity.created(null).body(TrajetoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<TrajetoResponse> listarPorId(
            @PathVariable int id) {
        var response = service.listarTrajetoCompleto(id);
        return ResponseEntity.ok().body(response);
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
        return ResponseEntity.noContent().build();
    }
}
