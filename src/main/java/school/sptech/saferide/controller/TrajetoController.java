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
import school.sptech.saferide.model.entity.trajeto.TrajetoMapper;
import school.sptech.saferide.model.entity.trajeto.TrajetoRequest;
import school.sptech.saferide.model.entity.trajeto.TrajetoResponse;
import school.sptech.saferide.service.TrajetoService;

import java.io.IOException;
import java.util.List;

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
        var response = service.listarPorId(id);
        return ResponseEntity.ok().body(TrajetoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/motorista/{motoristaId}")
    public ResponseEntity<List<TrajetoResponse>> listarPorMotorista(
            @PathVariable int motoristaId) {
        var response = service.listarTrajetosPorMotorista(motoristaId);
        return ResponseEntity.ok().body(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PatchMapping("/alterarAtivo/{trajetoId}")
    public ResponseEntity<TrajetoResponse> atualizarAtivo(
            @PathVariable int trajetoId,
            @RequestParam boolean ativo) {
        var response = service.atualizarAtivo(trajetoId, ativo);
        return ResponseEntity.ok().body(TrajetoMapper.toDto(response));
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/baixar-trajeto-motorista/{motoristaId}")
    public ResponseEntity<InputStreamResource> baixarTrajetos(
            @PathVariable int motoristaId) {
        var response = service.baixarTrajetos(motoristaId);
        return ResponseEntity.ok().body(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permissão")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping(value = "/ler-trajeto-motorista/{motoristaId}", consumes = "multipart/form-data")
    public ResponseEntity<Void> leTrajetos(
            @PathVariable int motoristaId,
            @RequestParam("file") MultipartFile file) {
        try {
            service.lerTrajetos(file.getInputStream());
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao processar o arquivo", e);
        }
    }

}
