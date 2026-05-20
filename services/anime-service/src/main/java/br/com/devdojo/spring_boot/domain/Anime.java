package br.com.devdojo.spring_boot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Anime {
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
