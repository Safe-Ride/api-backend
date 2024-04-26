package saferide.sptech.apibackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.UsuarioConstants;
import saferide.sptech.apibackend.dto.usuario.UsuarioRequestUpdate;
import saferide.sptech.apibackend.dto.usuario.UsuarioRequest;
import saferide.sptech.apibackend.dto.usuario.UsuarioResponse;
import saferide.sptech.apibackend.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping(UsuarioConstants.BASE_PATH)
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(
            @Valid @RequestBody UsuarioRequest body) {
        return ResponseEntity.created(null).body(usuarioService.criar(body));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar(){
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping(UsuarioConstants.LIST_BY_ID_PATH)
    public ResponseEntity<UsuarioResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(usuarioService.listarPorId(id));
    }

    @PutMapping(UsuarioConstants.UPDATE_PATH)
    public ResponseEntity<UsuarioResponse> atualizar(
            @PathVariable int id,
            @RequestBody UsuarioRequestUpdate request) {
        return ResponseEntity.ok(usuarioService.atualizar(id, request));
    }

    @DeleteMapping(UsuarioConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(usuarioService.remover(id));
    }
}
