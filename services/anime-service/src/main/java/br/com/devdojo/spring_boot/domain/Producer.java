package br.com.devdojo.spring_boot.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
public class Producer {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private String address;


}
