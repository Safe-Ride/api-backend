package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.endereco.EnderecoMapper;
import school.sptech.saferide.model.entity.endereco.EnderecoRequest;
import school.sptech.saferide.model.entity.endereco.EnderecoResponse;
import school.sptech.saferide.service.EnderecoService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.ENDERECO_BASE_PATH)
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @PostMapping
    public ResponseEntity<EnderecoResponse> criar(
            @Valid @RequestBody EnderecoRequest request) {
        var payload = EnderecoMapper.toEntity(request);
        var response = service.criar(
                payload,
                request.getUsuarioId());
        return ResponseEntity.created(null).body(EnderecoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<EnderecoResponse> listarPorId(
            @PathVariable int id) {
        var response = service.listarPorId(id);
        return ResponseEntity.ok(EnderecoMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.ENDERECO_LIST_BY_USUARIO_PATH)
    public ResponseEntity<List<EnderecoResponse>> listarPorUsuario(@PathVariable int usuarioId) {
        var response = service.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(EnderecoMapper.toDto(response));
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
