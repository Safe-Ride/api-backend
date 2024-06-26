package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.trajeto.*;
import saferide.sptech.apibackend.service.TrajetoService;
import saferide.sptech.apibackend.service.utils.Ordenacao;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.TRAJETO_BASE_PATH)
@RequiredArgsConstructor
public class TrajetoController {

    private final TrajetoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
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
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping
    public ResponseEntity<List<TrajetoResponse>> listar() {
        var response = service.listar();
        var responseQuickSort = Ordenacao.quickSort(response, 0, response.size()-1);
        return ResponseEntity.ok(TrajetoMapper.toDto(responseQuickSort));
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
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PutMapping(ControllerConstants.UPDATE_PATH)
    public ResponseEntity<TrajetoResponse> atualizar(
            @PathVariable int id,
            @RequestBody TrajetoRequestUpdate request) {
        var response = service.atualizar(
                id,
                request);
        return ResponseEntity.ok(TrajetoMapper.toDto(response));
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
        return ResponseEntity.ok(service.remover(id));
    }

    @ApiResponses()
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PatchMapping("/status")
    public ResponseEntity<TrajetoResponse> atualizarStatusPorId (
            @RequestBody TrajetoStatusUpdate request) {
        var response = service.atualizarStatus(request.getTrajetoId(), request.getDependenteId(), request.getEnderecoId(), request.getStatus());
        return ResponseEntity.ok().body(response);
    }

    @ApiResponses()
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PatchMapping("/desfazer")
    public ResponseEntity<TrajetoResponse> desfazer () {
        var response = service.desfazer();
        return ResponseEntity.ok().body(response);
    }

}
