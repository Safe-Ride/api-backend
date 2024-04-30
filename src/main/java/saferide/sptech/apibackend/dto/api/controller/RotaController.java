package saferide.sptech.apibackend.dto.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.RotaConstants;
import saferide.sptech.apibackend.dto.rota.RotaMapper;
import saferide.sptech.apibackend.dto.rota.RotaRequest;
import saferide.sptech.apibackend.dto.rota.RotaResponse;
import saferide.sptech.apibackend.entity.Rota;
import saferide.sptech.apibackend.service.RotaService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(RotaConstants.BASE_PATH)
public class RotaController {

    @Autowired
    private RotaService rotaService;

    @GetMapping
    public ResponseEntity<List<RotaResponse>> listarPorIdDependente(@RequestParam int dependenteId) {
        List<RotaResponse> responses = rotaService.listarRotasPorDependente(dependenteId);

        if (responses.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<RotaResponse> criar(@RequestBody RotaRequest request) throws IOException {
        return ResponseEntity.created(null).body(rotaService.criar(request));
    }
}
