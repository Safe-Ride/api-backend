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
import school.sptech.saferide.model.entity.solicitacao.SolicitacaoMapper;
import school.sptech.saferide.model.entity.solicitacao.SolicitacaoRequest;
import school.sptech.saferide.model.entity.solicitacao.SolicitacaoResponse;
import school.sptech.saferide.service.SolicitacaoService;

import java.util.List;

@Controller
@RequestMapping(ControllerConstants.SOLICITACAO_BASE_PATH)
@RequiredArgsConstructor
public class SolicitacaoController {

    private final SolicitacaoService service;

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<SolicitacaoResponse> criar(
            @Valid @RequestBody SolicitacaoRequest request) {
        var payload = SolicitacaoMapper.toEntity(request);
        var response = service.criar(
                payload,
                request.getMotoristaId(),
                request.getResponsavelId(),
                request.getEscolaId(),
                request.getEnderecoId(),
                request.getDependenteId());
        return ResponseEntity.created(null).body(SolicitacaoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<SolicitacaoResponse> listarPorId(
            @PathVariable int id) {
        var response = service.listarPorId(id);
        return ResponseEntity.ok(SolicitacaoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/motorista/{motoristaId}")
    public ResponseEntity<List<SolicitacaoResponse>> listarPorMotorista(
            @PathVariable int motoristaId) {
        var response = service.listarPorMotorista(motoristaId);
        if (response.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(SolicitacaoMapper.toDto(response));
    }

}
