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
    private final ProducerData producerData;
    private final Connection connectionMongoDb;


    public List<Producer> findAll() {
        return producerData.getProducers();
    }

    public Optional<Producer> findById(Long id) {
        return producerData.getProducers().stream()
                .filter(producer -> producer.getId().equals(id))
                .findFirst();
    }

    public List<Producer> findByName(String name) {
        log.debug(connectionMongoDb);
        return producerData.getProducers().stream().filter(producer -> producer.getName()
                .equalsIgnoreCase(name)).toList();
    }

    public Producer save(Producer producer) {
        producerData.getProducers().add(producer);
        return producer;
    }

    public void delete(Producer producer) {
        producerData.getProducers().remove(producer);
    }

    public void update(Producer producer) {
        delete(producer);
        save(producer);
    }
}
