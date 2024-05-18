package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.endereco.*;
import saferide.sptech.apibackend.service.EnderecoService;
import saferide.sptech.apibackend.service.ViaCepService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(ControllerConstants.ENDERECO_BASE_PATH)
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<EnderecoResponse> criar(
            @Valid @RequestBody EnderecoRequest request) {
        return ResponseEntity.created(null).body(EnderecoMapper.toDto(service.criar(request)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "Sem conteudo"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping
    public ResponseEntity<List<EnderecoResponse>> listar() {
        return ResponseEntity.ok(EnderecoMapper.toDto(service.listar()));
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
        return ResponseEntity.ok(EnderecoMapper.toDto(service.listarPorId(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PutMapping(ControllerConstants.UPDATE_PATH)
    public ResponseEntity<EnderecoResponse> atualizar(
            @PathVariable int id,
            @RequestBody EnderecoRequestUpdate request) {
        return ResponseEntity.ok(EnderecoMapper.toDto(service.atualizar(id, request)));
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.SEARCH_CEP)
    public ResponseEntity<ViaCepResponse> buscarCep(
            @PathVariable String cep) {
        ViaCepService viaCepService = new ViaCepService();
        try {
            ViaCepResponse viaCep = viaCepService.getEndereco(cep);

            if (viaCep.getCep() == null) return ResponseEntity.notFound().build();

            return ResponseEntity.ok(viaCep);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
