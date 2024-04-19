package saferide.sptech.apibackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.DependenteConstants;
import saferide.sptech.apibackend.dto.dependente.DependenteRequest;
import saferide.sptech.apibackend.dto.dependente.DependenteRequestUpdate;
import saferide.sptech.apibackend.dto.dependente.DependenteResponse;
import saferide.sptech.apibackend.repository.ClienteRepository;
import saferide.sptech.apibackend.repository.DependenteRepository;
import saferide.sptech.apibackend.service.DependenteService;

import javax.naming.NotContextException;
import java.util.List;

@RestController
@RequestMapping(DependenteConstants.BASE_PATH)
@RequiredArgsConstructor
public class DependenteController {

    private DependenteRepository dependenteRepository;
    private ClienteRepository clienteRepository;
    DependenteService dependenteService = new DependenteService(dependenteRepository, clienteRepository);

    @PostMapping
    public ResponseEntity<DependenteResponse> criar(
            @Valid @RequestBody DependenteRequest request) {
        return ResponseEntity.created(null).body(dependenteService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<DependenteResponse>> listar() throws NotContextException {
        return ResponseEntity.ok(dependenteService.listar());
    }

    @GetMapping(DependenteConstants.LIST_BY_ID_PATH)
    public ResponseEntity<DependenteResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(dependenteService.listarPorId(id));
    }

    @PutMapping(DependenteConstants.UPDATE_PATH)
    public ResponseEntity<DependenteResponse> atualizar(
            @PathVariable int id,
            @RequestBody DependenteRequestUpdate request) {
        return ResponseEntity.ok(dependenteService.atualizar(id, request));
    }

    @DeleteMapping(DependenteConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(dependenteService.remover(id));
    }
}
