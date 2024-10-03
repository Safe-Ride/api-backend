package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.autentication.UsuarioLoginDto;
import school.sptech.saferide.model.autentication.UsuarioTokenDto;
import school.sptech.saferide.model.entity.dependente.DependenteMapper;
import school.sptech.saferide.model.entity.dependente.DependenteResponse;
import school.sptech.saferide.model.entity.usuario.*;
import school.sptech.saferide.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.USUARIO_BASE_PATH)
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(
            @Valid @RequestBody UsuarioRequest request) {
        var payload = UsuarioMapper.toEntity(request);
        var response = service.criar(payload);
        return ResponseEntity.created(null).body(UsuarioMapper.toDto(response));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(
            @RequestBody UsuarioLoginDto usuarioLoginDto){
        UsuarioTokenDto usuarioTokenDto = this.service.autenticar(usuarioLoginDto);
        return  ResponseEntity.status(200).body(usuarioTokenDto);
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
        var response = service.listarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(response));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.USUARIO_DRIVER_CLIENTS_BASE_PATH)
    public ResponseEntity<List<MotoristaListarClientes>> listarResponsaveisPorMotorista(
            @PathVariable int id) {
        var response = service.listarResponsaveisPorMotorista(id);
        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.USUARIO_PARENT_DEPENDANTS_BASE_PATH)
    public ResponseEntity<List<DependenteResponse>> listarDependentesPorResponsavel(
            @PathVariable int id) {
        var response = service.listarDependentesPorResponsavel(id);
        return ResponseEntity.ok(DependenteMapper.toDto(response));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping("/motoristas-cliente/{id}")
    public ResponseEntity<List<ResponsavelListarMotoristas>> listarMotoristasPorCliente(
            @PathVariable int id) {
        var response = service.listarMotoristasPorCliente(id);
        return ResponseEntity.ok(UsuarioMapper.toListaMotoristas(response));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(value = "/foto-perfil/{id}", produces = "image/jpeg")
    public ResponseEntity<byte[]> consultarFotoPerfilPorId(
            @PathVariable int id) {
        var response = service.consultarFotoPerfilPorId(id);
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping("/perfil/atualizar-nome")
    public ResponseEntity<UsuarioResponse> atualizarNome(
            @RequestBody UsuarioUpdate request) {
        var response = service.atualizarNome(request.getId(), request.getAlteracao());
        return ResponseEntity.ok(UsuarioMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping("/perfil/atualizar-email")
    public ResponseEntity<UsuarioResponse> atualizarEmail(
            @RequestBody UsuarioUpdate request) {
        var response = service.atualizarEmail(request.getId(), request.getAlteracao());
        return ResponseEntity.ok(UsuarioMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping("/perfil/atualizar-cpf")
    public ResponseEntity<UsuarioResponse> atualizarCpf(
            @RequestBody UsuarioUpdate request) {
        var response = service.atualizarCpf(request.getId(), request.getAlteracao());
        return ResponseEntity.ok(UsuarioMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping("/perfil/atualizar-telefone")
    public ResponseEntity<UsuarioResponse> atualizarTelefone(
            @RequestBody UsuarioUpdate request) {
        var response = service.atualizarTelefone(request.getId(), request.getAlteracao());
        return ResponseEntity.ok(UsuarioMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    public ResponseEntity<UsuarioResponse> atualizarDataNascimento(
            @RequestBody UsuarioUpdate request) {
        var response = service.atualizarDataNascimento(request.getId(), request.getAlteracao());
        return ResponseEntity.ok(UsuarioMapper.toDto(response));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição")
    })
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PatchMapping(value = "/atualizar-foto-perfil/{id}", consumes = "image/*")
    public ResponseEntity<Void> atualizarFoto(@PathVariable int id,
                                              @RequestBody byte[] referenciaArquivoFoto,
                                              @RequestHeader("Content-Type") String contentType) {
        service.atualizarFotoPerfilPorId(id, referenciaArquivoFoto, contentType);
        return ResponseEntity.ok().build();
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
