package saferide.sptech.apibackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.TrajetoConstants;
import saferide.sptech.apibackend.dto.trajeto.TrajetoRequest;
import saferide.sptech.apibackend.dto.trajeto.TrajetoRequestUpdate;
import saferide.sptech.apibackend.dto.trajeto.TrajetoResponse;
import saferide.sptech.apibackend.repository.TrajetoRepository;
import saferide.sptech.apibackend.service.TrajetoService;

import javax.naming.NotContextException;
import java.util.List;

@RestController
@RequestMapping(TrajetoConstants.BASE_PATH)
@RequiredArgsConstructor
public class TrajetoController {

    private TrajetoRepository trajetoRepository;
    TrajetoService trajetoService = new TrajetoService(trajetoRepository);

    @PostMapping
    public ResponseEntity<TrajetoResponse> criar(
            @Valid @RequestBody TrajetoRequest request) {
        return ResponseEntity.created(null).body(trajetoService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<TrajetoResponse>> listar() throws NotContextException {
        return ResponseEntity.ok(trajetoService.listar());
    }

    @GetMapping(TrajetoConstants.LIST_BY_ID_PATH)
    public ResponseEntity<TrajetoResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(trajetoService.listarPorId(id));
    }

    @PutMapping(TrajetoConstants.UPDATE_PATH)
    public ResponseEntity<TrajetoResponse> atualizar(
            @PathVariable int id,
            @RequestBody TrajetoRequestUpdate request) {
        return ResponseEntity.ok(trajetoService.atualizar(id, request));
    }

    @DeleteMapping(TrajetoConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(trajetoService.remover(id));
    }
}
