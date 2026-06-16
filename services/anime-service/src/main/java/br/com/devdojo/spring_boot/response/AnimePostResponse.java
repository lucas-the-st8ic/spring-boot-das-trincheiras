package br.com.devdojo.spring_boot.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AnimePostResponse {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
