package br.com.devdojo.spring_boot.controller;

import br.com.devdojo.spring_boot.mapper.ProducerMapper;
import br.com.devdojo.spring_boot.request.ProducerPostRequest;
import br.com.devdojo.spring_boot.request.ProducerPutRequest;
import br.com.devdojo.spring_boot.response.ProducerGetResponse;
import br.com.devdojo.spring_boot.response.ProducerPostResponse;
import br.com.devdojo.spring_boot.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/producers")
@Slf4j
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerMapper mapper;
    private final ProducerService service;

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>>listAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all producers, param name {}", name);

        var producers = service.findAll(name);
        var producerGetResponses = mapper.toProducerGetResponseList(producers);
        return ResponseEntity.ok(producerGetResponses);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find producer by id: {}", id);

        var producer = service.findByIdOrThrowNotFound(id);

        var producerGetResponse = mapper.toproducerGetResponse(producer);

        return ResponseEntity.ok(producerGetResponse);
    }

    //Idempotente
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE,
    headers = "x-api-key=1234")
    public ResponseEntity<ProducerPostResponse> save(@RequestBody ProducerPostRequest producerPostRequest,
                                                     @RequestHeader HttpHeaders headers) {
        log.info(headers.toString());
        var producer = mapper.toProducer(producerPostRequest);

        var producerSaved = service.save(producer);

        var producerGetResponse = mapper.toproducerPostResponse(producerSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(producerGetResponse);
        //return ResponseEntity.status(HttpStatus.CREATED).body(producer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete producer by id: {}", id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProducerPutRequest request) {
        log.debug("Request to update producer {}", request);

        var producerToUpdate = mapper.toProducer(request);

        service.update(producerToUpdate);

        return ResponseEntity.noContent().build();
    }

}
