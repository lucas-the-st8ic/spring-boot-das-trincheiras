package br.com.devdojo.spring_boot.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Anime {
    private Long id;
    private String name;


    public Anime(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<Anime> getAnimes() {
        Anime hajimeNoIppo = new Anime(01L, "Hajime no Ippo");
        Anime fma = new Anime(02L, "Fullmetal Alchemist");
        Anime dbz = new Anime(03L, "Dragon Ball - Z");

        return List.of(hajimeNoIppo, fma, dbz);
    }

}
