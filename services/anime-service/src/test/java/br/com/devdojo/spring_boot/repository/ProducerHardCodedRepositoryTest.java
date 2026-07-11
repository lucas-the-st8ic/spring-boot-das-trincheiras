package br.com.devdojo.spring_boot.repository;

import br.com.devdojo.spring_boot.domain.Producer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProducerHardCodedRepositoryTest {

    @InjectMocks
    private ProducerHardCodedRepository repository;

    @Mock
    private ProducerData  producerData;
    private final List<Producer> producerList = new ArrayList<>();


    @BeforeEach
    void init () {
        var ufotable = Producer.builder().id(01L)
                .name("Ufotable").createdAt(LocalDateTime.now()).build();

        var witStudio = Producer.builder().id(02L)
                .name("Wit Studio").createdAt(LocalDateTime.now()).build();

        var studioGhibli = Producer.builder().id(03L)
                .name("Studio Ghibli").createdAt(LocalDateTime.now()).build();

        producerList.addAll(List.of(ufotable, witStudio, studioGhibli));

        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);
    }

    @Test
    @DisplayName("findAll return a list with all producers")
    void findAll_ReturnsAllProducers_WhenSuccessful () {

        var producers = repository.findAll();
        Assertions.assertThat(producers).isNotNull().hasSize(3);
    }
}