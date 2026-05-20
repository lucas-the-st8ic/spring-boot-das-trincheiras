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
        Producer hajimeNoIppo = new Producer(01L, "Hajime no Ippo");
        Producer fma = new Producer(02L, "Fullmetal Alchemist");
        Producer dbz = new Producer(03L, "Dragon Ball - Z");
        producers.addAll(List.of(hajimeNoIppo, fma, dbz));
    }

}
