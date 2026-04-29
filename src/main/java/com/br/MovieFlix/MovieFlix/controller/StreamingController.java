package com.br.MovieFlix.MovieFlix.controller;

import com.br.MovieFlix.MovieFlix.entity.Streaming;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping
    public ResponseEntity<List<Streaming>> findAll() {
        return ResponseEntity.ok(streamingService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Streaming> findById(@PathVariable Long id) {
        return ResponseEntity.ok(streamingService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Streaming> save(@Valid @RequestBody Streaming streaming) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(streamingService.create(request));
    }
    @PutMapping
    public ResponseEntity<Streaming> update(@Valid
        Long id
        @RequestBody Streaming streaming) {
        return ResponseEntity.ok(streamingService.update(request,id))
    }

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        streamingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }












}
