package br.com.devdojo.spring_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/heroes")
public class HeroController {
    private static final List<String>
            HEROES =
            List.of("Ippo", "Edward", "Endeavour", "Hawks");

    @GetMapping
    public List<String> listAllHeroes() {
        return HEROES;
    }

    @GetMapping("filter")
    public List<String> listAllHeroesParam(@RequestParam(
            required = false /*defaltValue= ""*/)
                                               String name) {
        return HEROES.stream()
                .filter(hero -> hero.equalsIgnoreCase(name))
                .toList();
    }

    @GetMapping("filterlist")
    public List<String> listAllHeroesParamLIst(@RequestParam(
            required = false /*defaltValue= ""*/)
                                               List<String> names) {
        return HEROES.stream()
                .filter(names::contains)
                .toList();
    }
}
