package br.com.devdojo.spring_boot.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Producer {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private static List<Producer> producers = new ArrayList<>();

    static {
        Producer mappa = new Producer(01L, "Madhouse", LocalDateTime.now());
        Producer studioBones = new Producer(02L, "Studio Bones", LocalDateTime.now());
        Producer toeiAnimation = new Producer(03L, "Toei Animation", LocalDateTime.now());
        producers.addAll(List.of(mappa, studioBones, toeiAnimation));
    }

    public static void setProducers(List<Producer> producers) {
        Producer.producers = producers;
    }

    public Producer(Long id, String name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static List<Producer> getProducers() {
        return Producer.producers;
    }

}
