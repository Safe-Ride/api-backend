package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.pagamento.PagamentoMapper;
import school.sptech.saferide.model.entity.pagamento.PagamentoRequest;
import school.sptech.saferide.model.entity.pagamento.PagamentoResponse;
import school.sptech.saferide.model.view.PagamentoStatusView;
import school.sptech.saferide.model.view.PagamentosTotalEfetuadosView;
import school.sptech.saferide.model.view.RendaBrutaMesView;
import school.sptech.saferide.service.PagamentoService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(ControllerConstants.PAGAMENTO_BASE_PATH)
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<PagamentoResponse> criar(
            @Valid @RequestBody PagamentoRequest request) {
        var payload = PagamentoMapper.toEntity(request);
        var response = service.criar(payload);
        return ResponseEntity.created(null).body(PagamentoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
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
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
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
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/pagamentos-total-efetuados")
    public ResponseEntity<List<PagamentosTotalEfetuadosView>> listarPagamentosTotalEfetuadosView() {
        var response = service.listarPagamentosTotalEfetuadosView();
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/baixar-pagamento-motorista/{motoristaId}")
    public ResponseEntity<InputStreamResource> baixarPagamentos(
            @PathVariable int motoristaId) {
        var response = service.baixarCsv(motoristaId);
        return ResponseEntity.ok().body(response);
    }
    }

