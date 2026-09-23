package com.movieflix.mapper;

import com.movieflix.dto.request.StreamingRequest;
import com.movieflix.dto.response.StreamingResponse;
import com.movieflix.entity.Streaming;

public final class StreamingMapper {

    private StreamingMapper() {
    }

    public static Streaming toStreaming(StreamingRequest request) {
        return Streaming.builder().name(request.name()).build();
    }

    public static StreamingResponse toResponse(Streaming streaming) {
        return new StreamingResponse(streaming.getId(), streaming.getName());
    }
}
