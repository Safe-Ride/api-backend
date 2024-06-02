package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.mensagem.MensagemMapper;
import saferide.sptech.apibackend.dto.mensagem.MensagemRequest;
import saferide.sptech.apibackend.dto.mensagem.MensagemResponse;
import saferide.sptech.apibackend.service.MensagemService;

@RestController
@RequestMapping(ControllerConstants.MENSAGEM_BASE_PATH)
@RequiredArgsConstructor
public class MensagemController {

    private final MensagemService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "400", description = "Má requisição"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<MensagemResponse> criar(
            @Valid @RequestBody MensagemRequest request) {
        var payload = MensagemMapper.toEntity(request);
        var response = service.criar(
                payload,
                request.getHistoricoId(),
                request.getUsuarioId(),
                request.getDependenteId());
        return ResponseEntity.created(null).body(MensagemMapper.toDto(response));
    }

}
