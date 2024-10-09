package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.mensagem.MensagemMapper;
import school.sptech.saferide.model.entity.mensagem.MensagemRequest;
import school.sptech.saferide.model.entity.mensagem.MensagemResponse;
import school.sptech.saferide.service.MensagemService;

@RestController
@RequestMapping(ControllerConstants.MENSAGEM_BASE_PATH)
@RequiredArgsConstructor
public class MensagemController {

    private final MensagemService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<MensagemResponse> criar(
            @Valid @RequestBody MensagemRequest request) {
        var payload = MensagemMapper.toEntity(request);
        var response = service.criar(
                payload,
                request.getConversaId(),
                request.getUsuarioId(),
                request.getDependenteId());
        return ResponseEntity.created(null).body(MensagemMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atualizado"),
            @ApiResponse(responseCode = "401", description = "Sem permissão")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PatchMapping("/marcar-lida/{id}")
    public ResponseEntity<MensagemResponse> marcarLida(@PathVariable("id") Integer id) {
        service.marcarLida(id);
        return ResponseEntity.noContent().build();
    }

}
