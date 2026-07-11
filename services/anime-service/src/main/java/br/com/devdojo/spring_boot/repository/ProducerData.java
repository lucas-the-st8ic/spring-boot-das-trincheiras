package br.com.devdojo.spring_boot.repository;

import br.com.devdojo.spring_boot.domain.Producer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProducerData {
    private final List<Producer> producers = new ArrayList<>();

     {
        Producer madHouse = Producer.builder().id(01L)
                .name("Madhouse").createdAt(LocalDateTime.now()).build();

        Producer studioBones = Producer.builder().id(02L)
                .name("Studio Bones").createdAt(LocalDateTime.now()).build();

        Producer toeiAnimation = Producer.builder().id(03L)
                .name("Toei Animation").createdAt(LocalDateTime.now()).build();

        producers.addAll(List.of(madHouse, studioBones, toeiAnimation));
    }

    public List<Producer> getProducers() {
        return producers;
    }
}
