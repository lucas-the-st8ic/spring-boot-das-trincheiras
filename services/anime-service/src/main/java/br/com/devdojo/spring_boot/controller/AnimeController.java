package br.com.devdojo.spring_boot.controller;

import br.com.devdojo.spring_boot.mapper.AnimeMapper;
import br.com.devdojo.spring_boot.request.AnimePostRequest;
import br.com.devdojo.spring_boot.request.AnimePutRequest;
import br.com.devdojo.spring_boot.response.AnimeGetResponse;
import br.com.devdojo.spring_boot.response.AnimePostResponse;
import br.com.devdojo.spring_boot.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeMapper mapper;
    private final AnimeService service;

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>>  listAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all animes, param name {}", name);

        var animes = service.findAll(name);
        var animeGetResponses = mapper.toAnimeGetResponseList(animes);
        return ResponseEntity.ok(animeGetResponses);
}

    @GetMapping("{id}")
    public ResponseEntity <AnimeGetResponse> findById(@PathVariable Long id) {
       log.debug("Request to find anime by id: {}", id);

        var anime = service.findByIdOrThrowNotFound(id);

        var animeGetResponse = mapper.toAnimeGetResponse(anime);

        return ResponseEntity.ok(animeGetResponse);
    }

    //Idempotente
    @PostMapping
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        log.debug("Request to save anime : {}", request);
        var anime = mapper.toAnime(request);

        var animeSaved = service.save(anime);

        var animeGetResponse = mapper.toAnimePostResponse(animeSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(animeGetResponse);
        //return ResponseEntity.status(HttpStatus.CREATED).body(anime);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete anime by id: {}", id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest request) {
        log.debug("Request to update anime {}", request);

        var animeToUpdate = mapper.toAnime(request);

        service.update(animeToUpdate);

        return ResponseEntity.noContent().build();
    }

}
