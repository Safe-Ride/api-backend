package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.rota.*;
import school.sptech.saferide.model.entity.rota.RotaMapper;
import school.sptech.saferide.model.entity.rota.RotaRequest;
import school.sptech.saferide.model.entity.rota.RotaResponse;
import school.sptech.saferide.model.entity.rota.RotaUpdateRequest;
import school.sptech.saferide.service.RotaService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.ROTA_BASE_PATH)
@RequiredArgsConstructor
public class RotaController {

    private final RotaService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PatchMapping("/status/{id}")
    public ResponseEntity<RotaResponse> atualizar(
            @PathVariable int id,
            @RequestBody RotaUpdateRequest request) {
        var response = service.atualizar(id, request);
        return ResponseEntity.ok().body(RotaMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<RotaResponse> criar(
            @RequestBody RotaRequest request) {
        var response = service.criar(request);
        return ResponseEntity.ok().body(RotaMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @DeleteMapping("/{id}")
    public ResponseEntity<RotaResponse> remover(
            @PathVariable int id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/map-box/listar-enderecos/{idTrajeto}")
    public ResponseEntity<List<RotaListarEnderecos>> ListarEnderecosPeloTrajeto(
            @PathVariable int idTrajeto) {
        var response = service.listarEnderecosPeloTrajeto(idTrajeto);
        return ResponseEntity.ok().body(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/map-box/listar-escola-endereco/{idTrajeto}")
    public ResponseEntity<RotaEscolaEndereco> ListarEscolaEndereco(
            @PathVariable int idTrajeto) {
        var response = service.listarEscolaEndereco(idTrajeto);
        return ResponseEntity.ok().body(response);
    }



}
