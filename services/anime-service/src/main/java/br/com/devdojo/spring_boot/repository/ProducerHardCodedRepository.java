package br.com.devdojo.spring_boot.repository;

import br.com.devdojo.spring_boot.domain.Producer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProducerHardCodedRepository {
    private static final List<Producer> PRODUCERS = new ArrayList<>();

    static {
        Producer madHouse = Producer.builder().id(01L)
                .name("Madhouse").createdAt(LocalDateTime.now()).build();

        Producer studioBones = Producer.builder().id(02L)
                .name("Studio Bones").createdAt(LocalDateTime.now()).build();

        Producer toeiAnimation = Producer.builder().id(03L)
                .name("Toei Animation").createdAt(LocalDateTime.now()).build();

        PRODUCERS.addAll(List.of(madHouse, studioBones, toeiAnimation));
    }

    public static List<Producer> findAll() {
        return Producer.PRODUCERS;
    }
}
