package br.com.devdojo.spring_boot.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Producer {
    private Long id;
    private String name;
    private static List<Producer> producers = new ArrayList<>();

    static {
        Producer mappa = new Producer(01L, "Madhouse");
        Producer studioBones = new Producer(02L, "Studio Bones");
        Producer toeiAnimation = new Producer(03L, "Toei Animation");
        producers.addAll(List.of(mappa, studioBones, toeiAnimation));
    }

    public static void setProducers(List<Producer> producers) {
        Producer.producers = producers;
    }

    public Producer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<Producer> getProducers() {
        return Producer.producers;
    }

}
