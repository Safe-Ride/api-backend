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
import school.sptech.saferide.model.entity.tempoReal.TempoRealResponse;
import school.sptech.saferide.service.TempoRealService;

@RestController
@RequestMapping(ControllerConstants.TEMPO_REAL_BASE_PATH)
@RequiredArgsConstructor
public class TempoRealController {

    private final TempoRealService tempoRealService;

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
        tempoRealService.save(tempoRealRequest, motoristaId);
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
    public ResponseEntity<TempoRealResponse> ultimoRegistro(@PathVariable Integer dependenteId) {
        TempoReal response = tempoRealService.findById(dependenteId);
        return ResponseEntity.ok().body(TempoRealMapper.toDto(response));
    }


}


