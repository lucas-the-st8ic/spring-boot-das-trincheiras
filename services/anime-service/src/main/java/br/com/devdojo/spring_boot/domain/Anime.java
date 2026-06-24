package br.com.devdojo.spring_boot.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Anime {
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    @Getter
    private static List<Anime> animes = new ArrayList<>();

    static {
        Anime hajimeNoIppo = new Anime(01L, "Hajime no Ippo");
        Anime fma = new Anime(02L, "Fullmetal Alchemist");
        Anime dbz = new Anime(03L, "Dragon Ball - Z");
        animes.addAll(List.of(hajimeNoIppo, fma, dbz));
    }

}
