package br.com.devdojo.spring_boot.repository;

import br.com.devdojo.spring_boot.domain.Producer;
import external.dependency.Connection;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Repository
@Log4j2
public class ProducerHardCodedRepository {
    private static final List<Producer> PRODUCERS = new ArrayList<>();

    private final Connection connectionMongoDb;

    static {
        Producer madHouse = Producer.builder().id(01L)
                .name("Madhouse").createdAt(LocalDateTime.now()).build();

        Producer studioBones = Producer.builder().id(02L)
                .name("Studio Bones").createdAt(LocalDateTime.now()).build();

        Producer toeiAnimation = Producer.builder().id(03L)
                .name("Toei Animation").createdAt(LocalDateTime.now()).build();

        PRODUCERS.addAll(List.of(madHouse, studioBones, toeiAnimation));
    }


    public List<Producer> findAll() {
        return PRODUCERS;
    }

    public Optional<Producer> findById(Long id) {
        return PRODUCERS.stream()
                .filter(producer -> producer.getId().equals(id))
                .findFirst();
    }

    public List<Producer> findByName(String name) {
        log.debug(connectionMongoDb);
        return PRODUCERS.stream().filter(producer -> producer.getName()
                .equalsIgnoreCase(name)).toList();
    }

    public Producer save(Producer producer) {
        PRODUCERS.add(producer);
        return producer;
    }

    public void delete(Producer producer) {
        PRODUCERS.remove(producer);
    }

    public void update(Producer producer) {
        delete(producer);
        save(producer);
    }
}
