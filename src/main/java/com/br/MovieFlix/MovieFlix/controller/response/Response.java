package com.br.MovieFlix.MovieFlix.controller.response;

import lombok.Builder;

@Builder
public record Response(Long id,String name) {
}
