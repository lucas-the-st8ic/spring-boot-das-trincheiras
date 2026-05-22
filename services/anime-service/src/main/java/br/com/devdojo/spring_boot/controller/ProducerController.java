package br.com.devdojo.spring_boot.controller;

import br.com.devdojo.spring_boot.domain.Producer;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/producers")
@Slf4j
@AllArgsConstructor
public class ProducerController {

    @GetMapping
    public List<Producer> listAll(@RequestParam(required = false)
                                  String name) {
        var producers = Producer.getProducers();

        if (name == null) {
            return producers;
        }

        return producers.stream().filter(producer -> producer.getName()
                .equalsIgnoreCase(name)).toList();
    }

    @GetMapping("{id}")
    public Producer findById(@PathVariable Long id) {

        return Producer.getProducers()
                .stream()
                .filter(producer -> producer.getId()
                        .equals(id))
                .findFirst().orElse(null);

    }

    //Idempotente
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE,
    headers = "x-api-key=1234")
    public ResponseEntity<Producer> save(@RequestBody Producer producer,
                         @RequestHeader HttpHeaders headers) {
        log.info(headers.toString());
        producer.setId(ThreadLocalRandom.current().nextLong(100_000));
        Producer.getProducers().add(producer);
        return ResponseEntity.ok(producer);
        //return ResponseEntity.status(HttpStatus.CREATED).body(producer);
    }


}
