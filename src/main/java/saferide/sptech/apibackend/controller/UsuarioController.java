package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.usuario.*;
import saferide.sptech.apibackend.service.UsuarioService;
import saferide.sptech.apibackend.service.autentication.UsuarioLoginDto;
import saferide.sptech.apibackend.service.autentication.UsuarioTokenDto;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ControllerConstants.USUARIO_BASE_PATH)
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar(){
        return ResponseEntity.ok(UsuarioMapper.toDto(service.listar()));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(
            @Valid @RequestBody UsuarioRequest body) {
        return ResponseEntity.created(null).body(UsuarioMapper.toDto(service.criar(body)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<UsuarioResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(UsuarioMapper.toDto(service.listarPorId(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PutMapping(ControllerConstants.UPDATE_PATH)
    public ResponseEntity<UsuarioResponse> atualizar(
            @PathVariable int id,
            @RequestBody UsuarioRequestUpdate request) {
        return ResponseEntity.ok(UsuarioMapper.toDto(service.atualizar(id, request)));
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
        return ResponseEntity.ok(service.remover(id));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(
            @RequestBody UsuarioLoginDto usuarioLoginDto){
        UsuarioTokenDto usuarioTokenDto = this.service.autenticar(usuarioLoginDto);
        return  ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping("/logoff")
    public ResponseEntity<String> logoff(
            @RequestBody Map<String, String> requestBody) {

        String token = requestBody.get("token");

        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body("Token não fornecido na solicitação.");
        }

        // Invalidar o token
        service.logoff(token);

        return ResponseEntity.ok("Logoff realizado com sucesso.");
    }

}
