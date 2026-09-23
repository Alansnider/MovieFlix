package com.movieflix.controller;

import com.movieflix.dto.request.StreamingRequest;
import com.movieflix.dto.response.StreamingResponse;
import com.movieflix.entity.Streaming;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.service.StreamingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
@Tag(name = "Streaming", description = "Recurso responsável pelo gerenciamento de serviços de streaming")
@SecurityRequirement(name = "bearerAuth")
public class StreamingController {

    private final StreamingService streamingService;

    @Operation(summary = "Listar serviços de streaming")
    @GetMapping
    public List<StreamingResponse> getAll() {
        return streamingService.findAll().stream().map(StreamingMapper::toResponse).toList();
    }

    @Operation(summary = "Salvar serviço de streaming")
    @PostMapping
    public ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest request) {
        Streaming saved = streamingService.save(StreamingMapper.toStreaming(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toResponse(saved));
    }

    @Operation(summary = "Buscar serviço de streaming por id")
    @GetMapping("/{id}")
    public StreamingResponse getById(@PathVariable Long id) {
        return StreamingMapper.toResponse(streamingService.findById(id));
    }

    @Operation(summary = "Deletar serviço de streaming")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        streamingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
