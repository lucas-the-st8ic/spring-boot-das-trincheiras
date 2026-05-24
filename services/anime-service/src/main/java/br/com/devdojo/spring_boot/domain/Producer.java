package br.com.devdojo.spring_boot.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Producer {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private String address;
    private static List<Producer> producers = new ArrayList<>();

    static {
        Producer mappa = Producer.builder().id(01L)
                .name("Madhouse").createdAt(LocalDateTime.now()).build();

        Producer studioBones = Producer.builder().id(02L)
                .name("Studio Bones").createdAt(LocalDateTime.now()).build();

        Producer toeiAnimation = Producer.builder().id(03L)
                .name("Toei Animation").createdAt(LocalDateTime.now()).build();

        producers.addAll(List.of(mappa, studioBones, toeiAnimation));
    }

    public static List<Producer> getProducers() {
        return Producer.producers;
    }

}
