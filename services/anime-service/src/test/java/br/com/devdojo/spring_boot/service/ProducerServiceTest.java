package br.com.devdojo.spring_boot.service;

import br.com.devdojo.spring_boot.domain.Producer;
import br.com.devdojo.spring_boot.repository.ProducerHardCodedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProducerServiceTest {

    @InjectMocks
    private ProducerService service;

    @Mock
    private ProducerHardCodedRepository repository;
    private List<Producer> producerList;

    @BeforeEach
    void init() {
        var ufotable = Producer.builder().id(01L)
                .name("Ufotable").createdAt(LocalDateTime.now()).build();
        var witStudio = Producer.builder().id(02L)
                .name("Wit Studio").createdAt(LocalDateTime.now()).build();
        var studioGhibli = Producer.builder().id(03L)
                .name("Studio Ghibli").createdAt(LocalDateTime.now()).build();
        producerList.addAll(List.of(ufotable, witStudio, studioGhibli));

    }

    @Test
    @DisplayName("findAll return a list with all producers when argument is null")
    @Order(1)
    void findAll_ReturnsAllProducers_WhenNameIsNull() {
        BDDMockito.when(repository.findAll()).thenReturn(producerList);

        var producers = service.findAll(null);
        Assertions.assertThat(producers).isNotNull().hasSameElementsAs(producerList);

    }

    @Test
    @DisplayName("findAll returns list with found object when name exists")
    @Order(2)
    void findByName_ReturnsFoundProducerInList_WhenNameIsFound() {
        var producer  = producerList.getFirst();
        var expectedProducersFound = Collections.singletonList(producer);
        BDDMockito.when(repository.findByName(producer.getName())).thenReturn(expectedProducersFound);

        var producersFound = service.findAll(producer.getName());
        Assertions.assertThat(producersFound).containsAll(expectedProducersFound);
    }

}