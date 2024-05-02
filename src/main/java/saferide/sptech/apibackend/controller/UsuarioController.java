package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.UsuarioConstants;
import saferide.sptech.apibackend.dto.usuario.*;
import saferide.sptech.apibackend.service.UsuarioService;
import saferide.sptech.apibackend.service.autentication.UsuarioLoginDto;
import saferide.sptech.apibackend.service.autentication.UsuarioTokenDto;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(UsuarioConstants.BASE_PATH)
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(
            @Valid @RequestBody UsuarioRequest body) {
        return ResponseEntity.created(null).body(UsuarioMapper.toDto(usuarioService.criar(body)));
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar(){
        return ResponseEntity.ok(UsuarioMapper.toDto(usuarioService.listar()));
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping(UsuarioConstants.LIST_BY_ID_PATH)
    public ResponseEntity<UsuarioResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(UsuarioMapper.toDto(usuarioService.listarPorId(id)));
    }
    @SecurityRequirement(name = "Bearer")
    @PutMapping(UsuarioConstants.UPDATE_PATH)
    public ResponseEntity<UsuarioResponse> atualizar(
            @PathVariable int id,
            @RequestBody UsuarioRequestUpdate request) {
        return ResponseEntity.ok(UsuarioMapper.toDto(usuarioService.atualizar(id, request)));
    }
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping(UsuarioConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(usuarioService.remover(id));
    }
    @SecurityRequirement(name = "Bearer")
    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto){
        UsuarioTokenDto usuarioTokenDto = this.usuarioService.autenticar(usuarioLoginDto);
        return  ResponseEntity.status(200).body(usuarioTokenDto);
    }
    @SecurityRequirement(name = "Bearer")
    @PostMapping("/logoff")
    public ResponseEntity<String> logoff(@RequestBody Map<String, String> requestBody) {

        String token = requestBody.get("token");


        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body("Token não fornecido na solicitação.");
        }

        // Invalidar o token
        usuarioService.logoff(token);

        return ResponseEntity.ok("Logoff realizado com sucesso.");
    }
}
