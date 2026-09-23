package com.movieflix.service;

import com.movieflix.entity.Streaming;
import com.movieflix.exception.ResourceNotFoundException;
import com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository streamingRepository;

    public List<Streaming> findAll() {
        return streamingRepository.findAll();
    }

    public Streaming save(Streaming streaming) {
        return streamingRepository.save(streaming);
    }

    public Streaming findById(Long id) {
        return streamingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Streaming não encontrado: " + id));
    }

    public void deleteById(Long id) {
        findById(id);
        streamingRepository.deleteById(id);
    }
}
