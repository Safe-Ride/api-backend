package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.pagamento.PagamentoMapper;
import saferide.sptech.apibackend.dto.pagamento.PagamentoRequest;
import saferide.sptech.apibackend.dto.pagamento.PagamentoResponse;
import saferide.sptech.apibackend.entity.view.PagamentoStatusView;
import saferide.sptech.apibackend.entity.view.PagamentosTotalEfetuadosView;
import saferide.sptech.apibackend.entity.view.RendaBrutaMesView;
import saferide.sptech.apibackend.service.PagamentoService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.PAGAMENTO_BASE_PATH)
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<List<PagamentoResponse>> criarContrato(
            @Valid @RequestBody PagamentoRequest request) {
        var payload = PagamentoMapper.toEntity(request);
        var response = service.criarContrato(payload, request.getCobradorId(), request.getPagadorId());
        return ResponseEntity.created(null).body(PagamentoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/motorista")
    public ResponseEntity<List<PagamentoResponse>> listarPorMotorista() {
        var response = service.listarPorMotorista();
        return ResponseEntity.ok(PagamentoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/responsavel")
    public ResponseEntity<List<PagamentoResponse>> listarPorResponsavel() {
        var response = service.listarPorResponsavel();
        return ResponseEntity.ok(PagamentoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
//    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/pagamento-status")
    public ResponseEntity<PagamentoStatusView> listarPagamentoStatusView() {
        var response = service.listarPagamentoStatusView();
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
//    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/renda-bruta-mes")
    public ResponseEntity<List<RendaBrutaMesView>> listarRendaBrutaMesView() {
        var response = service.listarRendaBrutaMesView();
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
//    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/pagamentos-total-efetuados")
    public ResponseEntity<List<PagamentosTotalEfetuadosView>> listarPagamentosTotalEfetuadosView() {
        var response = service.listarPagamentosTotalEfetuadosView();
        return ResponseEntity.ok(response);
    }

}
