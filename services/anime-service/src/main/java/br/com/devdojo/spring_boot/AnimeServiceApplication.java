package br.com.devdojo.spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {"outside.devdojo", "br.com.devdojo.spring_boot"})
public class AnimeServiceApplication {

	public static void main(String[] args) {
        var applicationContext = SpringApplication.run(AnimeServiceApplication.class, args);
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .forEach(System.out::println);
    }

}
