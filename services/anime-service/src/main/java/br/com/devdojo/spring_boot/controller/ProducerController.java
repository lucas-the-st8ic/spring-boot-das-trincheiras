package br.com.devdojo.spring_boot.controller;

import br.com.devdojo.spring_boot.domain.Producer;
import br.com.devdojo.spring_boot.mapper.ProducerMapper;
import br.com.devdojo.spring_boot.request.ProducerPutRequest;
import br.com.devdojo.spring_boot.request.ProducerPostRequest;
import br.com.devdojo.spring_boot.response.ProducerGetResponse;
import br.com.devdojo.spring_boot.service.ProducerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/producers")
@Slf4j
@AllArgsConstructor
public class ProducerController {
    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;
    private ProducerService service;

    public ProducerController() {
        this.service = new ProducerService();
    }

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>>listAll(@RequestParam(required = false)
                                                           String name) {

        log.debug("Request received to list all producers, param name {}", name);

        var producers = service.findAll(name);
        var producerGetResponses = MAPPER.toProducerGetResponseList(producers);
        return ResponseEntity.ok(producerGetResponses);
    }

/*    @GetMapping("{id}")
    public ResponseEntity <ProducerGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find producer by id: {}", id);

        var producerGetResponse = Producer.getProducers()
                .stream()
                .filter(producer -> producer.getId()
                        .equals(id))
                .findFirst()
                .map(MAPPER::toproducerGetResponse)
                .orElse(null);
        return ResponseEntity.ok(producerGetResponse);
    }*/
    

    //Idempotente
  /*  @PostMapping
    public ResponseEntity<ProducerPostResponse> save(@RequestBody ProducerPostRequest request) {
        log.debug("Request to save producer : {}", request);
        var producer = MAPPER.toProducer(request);

        Producer.getProducers().add(producer);

        var response = MAPPER.toProducerPostResponse(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }*/


/*    @GetMapping
    public List<Producer> listAll(@RequestParam(required = false)
                                  String name) {
        var producers = Producer.getProducers();

        if (name == null) {
            return producers;
        }

        return producers.stream().filter(producer -> producer.getName()
                .equalsIgnoreCase(name)).toList();
    }*/

    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find producer by id: {}", id);

        var producer = service.findByIdOrThrowNotFound(id);

        var producerGetResponse = MAPPER.toproducerGetResponse(producer);

        return ResponseEntity.ok(producerGetResponse);
    }
    //Idempotente
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE,
    headers = "x-api-key=1234")
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest,
                                                    @RequestHeader HttpHeaders headers) {
        log.info(headers.toString());
        var producer = MAPPER.toProducer(producerPostRequest);

        var producerSaved = service.save(producer);

        var producerGetResponse = MAPPER.toproducerGetResponse(producerSaved);

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

        var producerToUpdate = MAPPER.toProducer(request);

        service.update(producerToUpdate);

        return ResponseEntity.noContent().build();
    }

}
