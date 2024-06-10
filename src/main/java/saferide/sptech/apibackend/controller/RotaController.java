package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.rota.RotaMapper;
import saferide.sptech.apibackend.dto.rota.RotaRequest;
import saferide.sptech.apibackend.dto.rota.RotaResponse;
import saferide.sptech.apibackend.service.RotaService;
import saferide.sptech.apibackend.service.ViaCepService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(ControllerConstants.ROTA_BASE_PATH)
@RequiredArgsConstructor
public class RotaController {

    private final RotaService service;
    private final ViaCepService viaCepService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<RotaResponse> criar(
            @Valid @RequestBody RotaRequest request) throws IOException {
        var payload = RotaMapper.toEntity(request);
        var response = service.criar(payload, request.getTrajetoId(), request.getDependenteId(), request.getEnderecoId());
        var viaCepResponse = viaCepService.getEndereco(response.getEndereco().getCep());
        return ResponseEntity.created(null).body(RotaMapper.toDtoViaCep(response, viaCepResponse));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping
    public ResponseEntity<List<RotaResponse>> listarPorIdDependente(
            @RequestParam int dependenteId) {
        List<RotaResponse> responses = service.listarRotasPorDependente(dependenteId);

        if (responses.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(responses);
    }

}
