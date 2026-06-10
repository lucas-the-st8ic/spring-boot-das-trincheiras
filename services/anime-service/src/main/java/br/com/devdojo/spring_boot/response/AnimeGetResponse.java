package br.com.devdojo.spring_boot.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AnimeGetResponse {
    private Long id;
    private String name;
}
