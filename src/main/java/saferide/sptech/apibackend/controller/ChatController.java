package saferide.sptech.apibackend.controller;

import jakarta.validation.Valid;
import jdk.javadoc.doclet.Reporter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saferide.sptech.apibackend.constants.ChatConstants;
import saferide.sptech.apibackend.dto.chat.ChatMapper;
import saferide.sptech.apibackend.dto.chat.ChatRequest;
import saferide.sptech.apibackend.dto.chat.ChatResponse;
import saferide.sptech.apibackend.entity.Chat;
import saferide.sptech.apibackend.service.ChatService;

@RestController
@RequestMapping(ChatConstants.BASE_PATH)
@RequiredArgsConstructor
public class ChatController {

    private final ChatService service;

    @PostMapping
    public ResponseEntity<ChatResponse> criar(@Valid @RequestBody ChatRequest request) {
        return ResponseEntity.created(null).body(ChatMapper.toDto(service.criar(request)));
    }
    @GetMapping
    public ResponseEntity<ChatResponse> listar(
            @RequestParam int responsavelId,
            @RequestParam int motoristaId) {
        return ResponseEntity.ok(ChatMapper.toDto(service.listar(responsavelId, motoristaId)));
    }

    @GetMapping(ChatConstants.LIST_BY_ID)
    public ResponseEntity<ChatResponse> listar(@PathVariable int id) {
        return ResponseEntity.ok(ChatMapper.toDto(service.listarPorId(id)));
    }
}
