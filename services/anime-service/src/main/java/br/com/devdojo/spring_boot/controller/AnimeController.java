package br.com.devdojo.spring_boot.controller;

import br.com.devdojo.spring_boot.domain.Anime;
import br.com.devdojo.spring_boot.mapper.AnimeMapper;
import br.com.devdojo.spring_boot.response.AnimeGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {
    public static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;
    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>>  listAll(@RequestParam(required = false)
                                    String name) {

        log.debug("Request received to list all animes, param name {}", name);
        var animes = Anime.getAnimes();
        List<AnimeGetResponse> animeGetResponseList =
                MAPPER.toAnimeGetResponseList(animes);

        if(name == null) {
            return ResponseEntity.ok(animeGetResponseList);
        }

        var response = animeGetResponseList.stream().filter(anime -> anime.getName()
                .equalsIgnoreCase(name)).toList();

        return ResponseEntity.ok(response);
}

    @GetMapping("{id}")
    public ResponseEntity <AnimeGetResponse> findById(@PathVariable Long id) {
       log.debug("Request to find anime by id: {}", id);

        var animeGetResponse = Anime.getAnimes()
                .stream()
                .filter(anime -> anime.getId()
                        .equals(id))
                .findFirst()
               .map(MAPPER::toAnimeGetResponse)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));

        return ResponseEntity.ok(animeGetResponse);
    }

    //Idempotente
    @PostMapping
    public Anime save(@RequestBody Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(100_000));
        Anime.getAnimes().add(anime);
        return anime;
    }

}
