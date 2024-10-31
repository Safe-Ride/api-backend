package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.solicitacao.SolicitacaoMapper;
import school.sptech.saferide.model.entity.solicitacao.SolicitacaoRequest;
import school.sptech.saferide.model.entity.solicitacao.SolicitacaoRequestResponsavel;
import school.sptech.saferide.model.entity.solicitacao.SolicitacaoResponse;
import school.sptech.saferide.service.SolicitacaoService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.SOLICITACAO_BASE_PATH)
@RequiredArgsConstructor
public class SolicitacaoController {

    private final SolicitacaoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<SolicitacaoResponse> criar(
            @Valid @RequestBody SolicitacaoRequestResponsavel request) {
        var response = service.criar(request);
        return ResponseEntity.created(null).body(SolicitacaoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Criado"),
            @ApiResponse(responseCode = "401", description = "Sem permição"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PutMapping
    public ResponseEntity<SolicitacaoResponse> atualizar(
            @Valid @RequestBody SolicitacaoRequest request) {
        var response = service.atualizar(request);
        return ResponseEntity.ok(SolicitacaoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Criado"),
            @ApiResponse(responseCode = "401", description = "Sem permição"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PatchMapping("/{id}")
    public ResponseEntity<Void> cancelar(
            @PathVariable Integer id) {
        service.cancelar(id);
        return ResponseEntity.noContent().build();
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/responsavel/{responsavelId}")
    public ResponseEntity<List<SolicitacaoResponse>> listarPorResponsavel(
            @PathVariable int responsavelId) {
        var response = service.listarPorResponsavel(responsavelId);
        if (response.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(SolicitacaoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/dependente/{dependenteId}")
    public ResponseEntity<SolicitacaoResponse> listarPorDependente(
            @PathVariable int dependenteId) {
        var response = service.listarPorDependente(dependenteId);
        return ResponseEntity.ok(SolicitacaoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "401", description = "Sem permição"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/motorista/{motoristaId}/qtdSolicitacao")
    public ResponseEntity<Integer> listarQtdSolicitacaoPorMotorista(
            @PathVariable int motoristaId) {
        var response = service.listarQtdSolicitacao(motoristaId);
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PatchMapping("/aprovar/{id}")
    public ResponseEntity<SolicitacaoResponse> aprovar(
            @PathVariable int id) {
        var response = service.aprovar(id);
        return ResponseEntity.ok(SolicitacaoMapper.toDto(response));
    }

}
