package br.com.devdojo.spring_boot.controller;

import br.com.devdojo.spring_boot.domain.Producer;
import br.com.devdojo.spring_boot.mapper.ProducerMapper;
import br.com.devdojo.spring_boot.request.ProducerPostRequest;
import br.com.devdojo.spring_boot.response.ProducerGetResponse;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/producers")
@Slf4j
@AllArgsConstructor
public class ProducerController {
    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

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
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest,
                                                    @RequestHeader HttpHeaders headers) {
        log.info(headers.toString());
        var producer = MAPPER.toProducer(producerPostRequest);
        var response = MAPPER.toproducerGetResponse(producer);



        Producer.getProducers().add(producer);




        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        //return ResponseEntity.status(HttpStatus.CREATED).body(producer);
    }
}
