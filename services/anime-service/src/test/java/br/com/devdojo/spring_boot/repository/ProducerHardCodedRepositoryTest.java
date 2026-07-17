package br.com.devdojo.spring_boot.repository;

import br.com.devdojo.spring_boot.domain.Producer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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


    }

    @Test
    @DisplayName("findAll return a list with all producers")
    @Order(1)
    void findAll_ReturnsAllProducers_WhenSuccessful () {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producers = repository.findAll();
        Assertions.assertThat(producers).isNotNull().hasSameElementsAs(producerList);

    }

    @Test
    @DisplayName("findById return a producer with given id")
    void findById_ReturnsProducerById_WhenSuccessful () {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var expectedProducer = producerList.getFirst();
        var producers = repository.findById(expectedProducer.getId());
        Assertions.assertThat(producers).isPresent().contains(expectedProducer);

    }

    @Test
    @DisplayName("findByName returns an empty list when name is null")
    void findByName_ReturnsAllProducers_WhenNameIsNull() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producers = repository.findByName(null);
        Assertions.assertThat(producers).isNotNull().isEmpty();

    }

    @Test
    @DisplayName("findByName returns list with found object when name exists")
    void findByName_ReturnsFoundProducerInList_WhenNameIsFound() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var expectedProducer = producerList.getFirst();
        var producers = repository.findByName(expectedProducer.getName());
        Assertions.assertThat(producers).hasSize(1).contains(expectedProducer);
    }

    @Test
    @DisplayName("save creates a producer")
    @Order(5)
    void save_CreatesProducer_WhenSuccessful () {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producerToSave = Producer.builder().id(99L)
                .name("Avatar Studios").createdAt(LocalDateTime
                        .now()).build();
        var producer = repository.save(producerToSave);

        Assertions.assertThat(producer).isEqualTo(producerToSave);

        var produerSavedOptional = repository.findById(producerToSave.getId());
        Assertions.assertThat(produerSavedOptional).isPresent().contains(producerToSave);
    }

    @Test
    @DisplayName("delete removes a producer")
    @Order(6)
    void delete_RemovesProducer_WhenSuccessful () {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producerToDelete = producerList.getFirst();
        repository.delete(producerToDelete);
        var producers = repository.findAll();
        Assertions.assertThat(producers).isNotEmpty().doesNotContain(producerToDelete);

    }

    @Test
    @DisplayName("update updates a producer")
    @Order(7)
    void update_UpdatesProducer_WhenSuccessful () {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producerToUpdate = this.producerList.getFirst();
        producerToUpdate.setName("Aniplex");

        repository.update(producerToUpdate);

        Assertions.assertThat(this.producerList).contains(producerToUpdate);
        var producerUpdatedOptional = repository.findById(producerToUpdate.getId());
        Assertions.assertThat(producerUpdatedOptional).isPresent();
        Assertions.assertThat(producerUpdatedOptional.get().getName()).isEqualTo(producerToUpdate.getName());
    }
}