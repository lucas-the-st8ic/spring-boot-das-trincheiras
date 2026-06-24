package br.com.devdojo.spring_boot.repository;

import br.com.devdojo.spring_boot.domain.Anime;
import br.com.devdojo.spring_boot.domain.Anime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimeHardCodedRepository {
    private static final List<Anime> ANIMES = new ArrayList<>();

    static {
        Anime hajimeNoIppo = Anime.builder().id(01L)
                .name("Hajime no Ippo").build();

        Anime fma = Anime.builder().id(02L)
                .name("Fullmetal Alchemist").build();

        Anime dbz = Anime.builder().id(03L)
                .name("Dragon Ball - Z").build();

        ANIMES.addAll(List.of(hajimeNoIppo, fma, dbz));
    }
    
    public  List<Anime> findAll() {
        return ANIMES;
    }

    public Optional<Anime> findById(Long id) {
       return ANIMES.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst();
    }

    public List<Anime> findByName(String name) {
        return ANIMES.stream().filter(anime -> anime.getName()
                .equalsIgnoreCase(name)).toList();
    }

    public Anime save(Anime anime) {
        ANIMES.add(anime);
        return anime;
    }

    public void delete(Anime anime) {
        ANIMES.remove(anime);
    }

    public void update(Anime anime) {
        delete(anime);
        save(anime);
    }
}
