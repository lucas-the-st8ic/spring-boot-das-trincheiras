package br.com.devdojo.spring_boot.service;

import br.com.devdojo.spring_boot.domain.Anime;
import br.com.devdojo.spring_boot.repository.AnimeHardCodedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnimeServiceTest {

    @InjectMocks
    private AnimeService service;

    @Mock
    private AnimeHardCodedRepository repository;
    private List<Anime> animeList = new ArrayList<>();

    @BeforeEach
    void init() {
        Anime hajimeNoIppo = Anime.builder().id(01L)
                .name("Hajime no Ippo").build();

        Anime fma = Anime.builder().id(02L)
                .name("Fullmetal Alchemist").build();

        Anime dbz = Anime.builder().id(03L)
                .name("Dragon Ball - Z").build();

        animeList.addAll(List.of(hajimeNoIppo, fma, dbz));

    }

    @Test
    @DisplayName("findAll return a list with all animes when argument is null")
    @Order(1)
    void findAll_ReturnsAllAnimes_WhenNameIsNull() {
        BDDMockito.when(repository.findAll()).thenReturn(animeList);
        var animes = service.findAll(null);
        Assertions.assertThat(animes).isNotNull().hasSameElementsAs(animeList);

    }

    @Test
    @DisplayName("findAll returns list with found object when name exists")
    @Order(2)
    void findByName_ReturnsFoundAnimeInList_WhenNameIsFound() {
        var anime  = animeList.getFirst();
        var expectedAnimesFound = Collections.singletonList(anime);
        BDDMockito.when(repository.findByName(anime.getName())).thenReturn(expectedAnimesFound);

        var animesFound = service.findAll(anime.getName());
        Assertions.assertThat(animesFound).containsAll(expectedAnimesFound);
    }

    @Test
    @DisplayName("findAll returns an empty list when name is not found")
    @Order(3)
    void findByName_ReturnsEmptyList_WhenNameIsNull() {
        var name = "not-found";

        BDDMockito.when(repository.findByName(name)).thenReturn(Collections.emptyList());

        var animes = service.findAll(name);
        Assertions.assertThat(animes).isNotNull().isEmpty();

    }

    @Test
    @DisplayName("findById return a anime with given id")
    @Order(4)
    void findById_ReturnsAnimeById_WhenSuccessful() {
        var expectedAnime = animeList.getFirst();
        BDDMockito.when(repository.findById(expectedAnime.getId()))
                .thenReturn(Optional.of(expectedAnime));

        var animes = service.findByIdOrThrowNotFound(expectedAnime.getId());
        Assertions.assertThat(animes).isEqualTo(expectedAnime);

    }

    @Test
    @DisplayName("findById throws ResponseStatusException when anime is not found")
    @Order(5)
    void findById_ThrowsResponseStatusException_WhenAnimeIsNotFound() {
        var expectedAnime = animeList.getFirst();
        BDDMockito.when(repository.findById(expectedAnime.getId()))
                .thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() ->service.findByIdOrThrowNotFound(expectedAnime.getId()))
                .isInstanceOf(ResponseStatusException.class);


    }

    @Test
    @DisplayName("save creates a anime")
    @Order(6)
    void save_CreatesAnime_WhenSuccessful() {

        var animeToSave = Anime.builder().id(99L)
                .name("Avatar Studios")
                .build();

        BDDMockito.when(repository.save(animeToSave)).thenReturn(animeToSave);

        var savedAnime = service.save(animeToSave);

        Assertions.assertThat(savedAnime).isEqualTo(animeToSave).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("delete removes a anime")
    @Order(7)
    void delete_RemovesAnime_WhenSuccessful() {
        var animeTodelete = animeList.getFirst();
        BDDMockito.when(repository.findById(animeTodelete.getId()))
                .thenReturn(Optional.of(animeTodelete));
        BDDMockito.doNothing().when(repository).delete(animeTodelete);
        Assertions.assertThatNoException().isThrownBy(() ->service.delete(animeTodelete.getId()));

    }

    @Test
    @DisplayName("delete throws ResponseStatusException when anime is not found")
    @Order(8)
    void delete_ThrowsResponseStatusException_WhenAnimeIsNotFound() {
        var animeTodelete = animeList.getFirst();
        BDDMockito.when(repository.findById(animeTodelete.getId()))
                .thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() ->service.delete(animeTodelete.getId()))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("update updates a anime")
    @Order(9)
    void update_UpdatesAnime_WhenSuccessful() {

        var animeToUpdate = animeList.getFirst();
        animeToUpdate.setName("Aniplex");

        BDDMockito.when(repository.findById(animeToUpdate.getId()))
                .thenReturn(Optional.of(animeToUpdate));
        BDDMockito.doNothing().when(repository).update(animeToUpdate);

        Assertions.assertThatNoException().isThrownBy(() ->service.update(animeToUpdate));

    }

    @Test
    @DisplayName("update throws ResponseStatusException when anime is not found")
    @Order(10)
    void update_ThrowsResponseStatusException_WhenAnimeIsNotFound() {

        var animeToUpdate = animeList.getFirst();

        BDDMockito.when(repository.findById(animeToUpdate.getId()))
                .thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() ->service.update(animeToUpdate))
                .isInstanceOf(ResponseStatusException.class);
    }


}