package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.escola.EscolaMapper;
import school.sptech.saferide.model.entity.escola.EscolaRequest;
import school.sptech.saferide.model.entity.escola.EscolaResponse;
import school.sptech.saferide.service.EscolaService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.ESCOLAS_BASE_PATH)
@RequiredArgsConstructor
public class EscolaController {

    private final EscolaService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<EscolaResponse> criar(
            @Valid @RequestBody EscolaRequest request) {
        var payload = EscolaMapper.toEntity(request);
        var response = service.criar(
                payload,
                request.getEnderecoId());
        return ResponseEntity.created(null).body(EscolaMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping()
    public ResponseEntity<List<EscolaResponse>> listar() {
        var response = service.listar();
        return ResponseEntity.ok(EscolaMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<EscolaResponse> listarPorId(
            @PathVariable int id) {
        var response = service.listarPorId(id);
        return ResponseEntity.ok(EscolaMapper.toDto(response));
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
        var response = service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
