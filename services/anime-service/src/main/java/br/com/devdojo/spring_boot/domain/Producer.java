package br.com.devdojo.spring_boot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Producer {
    private Long id;
    private String name;
    @Getter
    private static List<Producer> producers = new ArrayList<>();

    static {
        Producer mappa = new Producer(001L, "Madhouse");
        Producer studioBones = new Producer(002L, "Studio Bones");
        Producer toeiAnimation = new Producer(003L, "Toei Animation");
        producers.addAll(List.of(mappa, studioBones, toeiAnimation));
    }

}
