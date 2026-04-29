package com.br.MovieFlix.MovieFlix.mapper;

import com.br.MovieFlix.MovieFlix.controller.request.StreamingRequest;
import com.br.MovieFlix.MovieFlix.controller.response.StreamingResponse;
import com.br.MovieFlix.MovieFlix.entity.Streaming;
import org.springframework.stereotype.Component;

@Component
public class StreamingMapper {

    public Streaming toStreaming(StreamingRequest streamingRequest){
        return  Streaming.builder()
                .name(streamingRequest.name())
                .build();
    }

    public StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse.builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }


}
