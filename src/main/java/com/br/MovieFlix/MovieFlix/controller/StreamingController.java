package com.br.MovieFlix.MovieFlix.controller;

import com.br.MovieFlix.MovieFlix.controller.request.StreamingRequest;
import com.br.MovieFlix.MovieFlix.controller.response.StreamingResponse;
import com.br.MovieFlix.MovieFlix.service.StreamingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> findAll() {
        return ResponseEntity.ok(streamingService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(streamingService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest create) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(streamingService.create(create));
    }
    @PutMapping
    public ResponseEntity<StreamingResponse> update(@Valid @RequestBody StreamingRequest request,@PathVariable Long id) {
        return ResponseEntity.ok(streamingService.update(id, request));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        streamingService.delete(id);
        return ResponseEntity.noContent().build();
    }












}
