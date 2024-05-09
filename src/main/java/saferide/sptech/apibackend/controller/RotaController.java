package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.rota.RotaRequest;
import saferide.sptech.apibackend.dto.rota.RotaResponse;
import saferide.sptech.apibackend.service.RotaService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(ControllerConstants.ROTA_BASE_PATH)
@RequiredArgsConstructor
public class RotaController {

    private final RotaService service;

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<RotaResponse> criar(
            @Valid @RequestBody RotaRequest request) throws IOException {
        return ResponseEntity.created(null).body(service.criar(request));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping
    public ResponseEntity<List<RotaResponse>> listarPorIdDependente(
            @RequestParam int dependenteId) {
        List<RotaResponse> responses = service.listarRotasPorDependente(dependenteId);

        if (responses.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(responses);
    }

}
