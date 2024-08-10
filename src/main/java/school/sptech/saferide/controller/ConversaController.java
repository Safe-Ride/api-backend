package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.conversa.ConversaMapper;
import school.sptech.saferide.model.entity.conversa.ConversaRequest;
import school.sptech.saferide.model.entity.conversa.ConversaResponse;
import school.sptech.saferide.service.ConversaService;

@RestController
@RequestMapping(ControllerConstants.CONVERSA_BASE_PATH)
@RequiredArgsConstructor
public class ConversaController {

    private final ConversaService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<ConversaResponse> criar(
            @Valid @RequestBody ConversaRequest request) {
        var payload = ConversaMapper.toEntity(request);
        var response = service.criar(
                payload,
                request.getResponsavelId(),
                request.getMotoristaId());
        return ResponseEntity.created(null).body(ConversaMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping
    public ResponseEntity<ConversaResponse> listarEntreMotoristaEResponsavel(
            @RequestParam int responsavelId,
            @RequestParam int motoristaId) {
        var response = service.listarEntreMotoristaEResponsavel(
                responsavelId,
                motoristaId);
        return ResponseEntity.ok(ConversaMapper.toDto(response));
    }
}
