package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.TransporteEscolaConstants;
import saferide.sptech.apibackend.dto.transporteEscola.TransporteEscolaMapper;
import saferide.sptech.apibackend.dto.transporteEscola.TransporteEscolaRequest;
import saferide.sptech.apibackend.dto.transporteEscola.TransporteEscolaResponse;
import saferide.sptech.apibackend.service.TransporteEscolaService;

import java.util.List;

@RestController
@RequestMapping(TransporteEscolaConstants.BASE_PATH)
@RequiredArgsConstructor
public class TransporteEscolaController {

    private final TransporteEscolaService transporteEscolaService;
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<TransporteEscolaResponse> criar(
            @Valid @RequestBody TransporteEscolaRequest request) {
        return ResponseEntity.created(null).body(TransporteEscolaMapper.toDto(transporteEscolaService.criar(request)));
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<TransporteEscolaResponse>> listar() {
        return ResponseEntity.ok(TransporteEscolaMapper.toDto(transporteEscolaService.listar()));
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping(TransporteEscolaConstants.LIST_BY_ID_PATH)
    public ResponseEntity<TransporteEscolaResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(TransporteEscolaMapper.toDto(transporteEscolaService.listarPorId(id)));
    }
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping(TransporteEscolaConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(transporteEscolaService.remover(id));
    }
}
