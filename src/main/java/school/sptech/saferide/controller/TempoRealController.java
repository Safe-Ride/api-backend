package school.sptech.saferide.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.saferide.constants.ControllerConstants;
import school.sptech.saferide.model.entity.tempoReal.TempoReal;
import school.sptech.saferide.model.entity.tempoReal.TempoRealMapper;
import school.sptech.saferide.model.entity.tempoReal.TempoRealRequest;
import school.sptech.saferide.service.TempoRealSersvice;

@RestController
@RequestMapping(ControllerConstants.TEMPO_REAL_BASE_PATH)
@RequiredArgsConstructor
public class TempoRealController {

    private final TempoRealSersvice tempoRealSersvice;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição"),
            @ApiResponse(responseCode = "400", description = "Tipo de usuario inválido")
    })
    @PostMapping(ControllerConstants.TEMPO_REAL_CREATION_BASE_PATH)
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    public ResponseEntity<Void> adicionarTempoReal(
            @RequestBody TempoRealRequest tempoRealRequest,
            @PathVariable Integer motoristaId
    ) {
        tempoRealSersvice.save(tempoRealRequest, motoristaId);
        return ResponseEntity.ok().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "401", description = "Sem permição"),
            @ApiResponse(responseCode = "400", description = "Tipo de usuario inválido")
    })
    @GetMapping(ControllerConstants.TEMPO_REAL_LAST_BASE_PATH)
    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    public ResponseEntity<TempoRealRequest> ultimoRegistro(@PathVariable Integer motoristaId) {
        TempoReal response = tempoRealSersvice.findById(motoristaId);
        return ResponseEntity.ok().body(TempoRealMapper.toDto(response));
    }


}


