package br.com.devdojo.spring_boot.repository;

import br.com.devdojo.spring_boot.domain.Anime;
import br.com.devdojo.spring_boot.domain.Anime;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnimeData {
    private final List<Anime> animes = new ArrayList<>();

     {
        Anime hajimeNoIppo = Anime.builder().id(01L)
                .name("Hajime no Ippo").build();

        Anime fma = Anime.builder().id(02L)
                .name("Fullmetal Alchemist").build();

        Anime dbz = Anime.builder().id(03L)
                .name("Dragon Ball - Z").build();

        animes.addAll(List.of(hajimeNoIppo, fma, dbz));
    }
    public List<Anime> getAnimes() {
        return animes;
    }
}
