package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.mensagem.MensagemMapper;
import saferide.sptech.apibackend.dto.mensagem.MensagemRequest;
import saferide.sptech.apibackend.dto.mensagem.MensagemResponse;
import saferide.sptech.apibackend.service.MensagemService;

@RestController
@RequestMapping(ControllerConstants.MENSAGEM_BASE_PATH)
@RequiredArgsConstructor
public class MensagemController {

    private final MensagemService service;

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<MensagemResponse> criar(
            @Valid @RequestBody MensagemRequest request) {
        return ResponseEntity.created(null).body(MensagemMapper.toDto(service.criar(request)));
    }

}
