package saferide.sptech.apibackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.MensagemConstants;
import saferide.sptech.apibackend.dto.mensagem.MensagemMapper;
import saferide.sptech.apibackend.dto.mensagem.MensagemRequest;
import saferide.sptech.apibackend.dto.mensagem.MensagemResponse;
import saferide.sptech.apibackend.service.MensagemService;

@RestController
@RequestMapping(MensagemConstants.BASE_PATH)
@RequiredArgsConstructor
public class MensagemController {

    private final MensagemService service;

    @PostMapping
    public ResponseEntity<MensagemResponse> criar(@Valid @RequestBody MensagemRequest request) {
        return ResponseEntity.created(null).body(MensagemMapper.toDto(service.criar(request)));
    }

}
