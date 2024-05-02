package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.EnderecoConstants;
import saferide.sptech.apibackend.dto.endereco.*;
import saferide.sptech.apibackend.service.EnderecoService;
import saferide.sptech.apibackend.service.ViaCepService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(EnderecoConstants.BASE_PATH)
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<EnderecoResponse> criar(
            @Valid @RequestBody EnderecoRequest request) {
        return ResponseEntity.created(null).body(EnderecoMapper.toDto(enderecoService.criar(request)));
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<EnderecoResponse>> listar() {
        return ResponseEntity.ok(EnderecoMapper.toDto(enderecoService.listar()));
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping(EnderecoConstants.LIST_BY_ID_PATH)
    public ResponseEntity<EnderecoResponse> listarPorId(
            @PathVariable int id) {
        return ResponseEntity.ok(EnderecoMapper.toDto(enderecoService.listarPorId(id)));
    }
    @SecurityRequirement(name = "Bearer")
    @PutMapping(EnderecoConstants.UPDATE_PATH)
    public ResponseEntity<EnderecoResponse> atualizar(
            @PathVariable int id,
            @RequestBody EnderecoRequestUpdate request) {
        return ResponseEntity.ok(EnderecoMapper.toDto(enderecoService.atualizar(id, request)));
    }
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping(EnderecoConstants.REMOVE_PATH)
    public ResponseEntity<Void> remover(
            @PathVariable int id) {
        return ResponseEntity.ok(enderecoService.remover(id));
    }

    @SecurityRequirement(name = "Bearer")
    @GetMapping(EnderecoConstants.SEARCH_CEP)
    public ResponseEntity<ViaCepResponse> buscarCep(
            @PathVariable String cep
    ) {
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
