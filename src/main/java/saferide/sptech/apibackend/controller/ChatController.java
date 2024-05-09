package saferide.sptech.apibackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ControllerConstants;
import saferide.sptech.apibackend.dto.chat.ChatMapper;
import saferide.sptech.apibackend.dto.chat.ChatRequest;
import saferide.sptech.apibackend.dto.chat.ChatResponse;
import saferide.sptech.apibackend.service.ChatService;

@RestController
@RequestMapping(ControllerConstants.CHAT_BASE_PATH)
@RequiredArgsConstructor
public class ChatController {

    private final ChatService service;

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @PostMapping
    public ResponseEntity<ChatResponse> criar(
            @Valid @RequestBody ChatRequest request) {
        return ResponseEntity.created(null).body(ChatMapper.toDto(service.criar(request)));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping
    public ResponseEntity<ChatResponse> listar(
            @RequestParam int responsavelId,
            @RequestParam int motoristaId) {
        return ResponseEntity.ok(ChatMapper.toDto(service.listar(responsavelId, motoristaId)));
    }

    @SecurityRequirement(name = ControllerConstants.SECURITY_NAME)
    @GetMapping(ControllerConstants.LIST_BY_ID_PATH)
    public ResponseEntity<ChatResponse> listar(
            @PathVariable int id) {
        return ResponseEntity.ok(ChatMapper.toDto(service.listarPorId(id)));
    }

}
