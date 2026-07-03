package br.com.devdojo.spring_boot.config;

import external.dependency.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConnectionConfiguration {

    @Bean

    public Connection connectionMySql() {
        return new Connection("localhost", "lucas_the_st8ic", "Lss@Ann");
    }

    @Bean(name = "connectionMongoDb")
    //@Primary
    public Connection connectionMongoDb() {
        return new Connection("localhost", "lucas_the_st8ic", "Lss@Ann");
    }
}
