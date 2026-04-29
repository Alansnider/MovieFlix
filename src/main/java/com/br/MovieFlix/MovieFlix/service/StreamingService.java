package com.br.MovieFlix.MovieFlix.service;

import com.br.MovieFlix.MovieFlix.controller.request.StreamingRequest;
import com.br.MovieFlix.MovieFlix.controller.response.StreamingResponse;
import com.br.MovieFlix.MovieFlix.entity.Streaming;
import com.br.MovieFlix.MovieFlix.repository.StreamingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository streamingRepository;
    private final StreamingMapper streamingMapper;

    public List<StreamingResponse> list(){
        return streamingRepository.findAll()
                .stream().map(streamingMapper::toStreaming)
                .toList();
    }

    public List<StreamingResponse> findById(Long id){
        Streaming streaming = streamingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Streaming not found");
                return streamingMapper.toStreaming(streaming);
    }

    public StreamingResponse update(Long id, StreamingRequest streamingRequest){
        Streaming existing = streamingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Streaming not found"));
        existing.setName(streamingRequest.name());
        Streaming streaming = streamingRepository.save(existing);
        return streamingMapper.toStreaming(streaming);
    }

    public StreamingResponse create(StreamingRequest streamingRequest){
        Streaming create = streamingRepository.save(streamingMapper.toStreaming(request));
                return streamingMapper.toStreamingResponse(create);
    }

    public void delete(Long id){
        Streaming streamingg = streamingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Streaming not found"));
        streamingRepository.delete(streamingg);

    }





}
