package br.com.devdojo.spring_boot.repository;

import br.com.devdojo.spring_boot.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnimeHardCodedRepositoryTest {

    @InjectMocks
    private AnimeHardCodedRepository repository;

    @Mock
    private AnimeData AnimeData;
    private List<Anime> AnimeList;

    @BeforeEach
    void init() {
        var hajimeNoIppo = Anime.builder().id(01L)
                .name("HajimeNo Ippo").build();
        var fma = Anime.builder().id(02L)
                .name("Fullmetal Alchemist").build();

        var dbz = Anime.builder().id(03L)
                .name("Dragon Ball Z").build();

        AnimeList = new ArrayList<>(List.of(hajimeNoIppo, fma, dbz));


    }

    @Test
    @DisplayName("findAll return a list with all Animes")
    @Order(1)
    void findAll_ReturnsAllAnimes_WhenSuccessful() {
        BDDMockito.when(AnimeData.getAnimes()).thenReturn(AnimeList);

        var Animes = repository.findAll();
        Assertions.assertThat(Animes).isNotNull().hasSameElementsAs(AnimeList);

    }

    @Test
    @DisplayName("findById return an Anime with given id")
    void findById_ReturnsAnimeById_WhenSuccessful() {
        BDDMockito.when(AnimeData.getAnimes()).thenReturn(AnimeList);

        var expectedAnime = AnimeList.getFirst();
        var Animes = repository.findById(expectedAnime.getId());
        Assertions.assertThat(Animes).isPresent().contains(expectedAnime);

    }

    @Test
    @DisplayName("findByName returns an empty list when name is null")
    void findByName_ReturnsAllAnimes_WhenNameIsNull() {
        BDDMockito.when(AnimeData.getAnimes()).thenReturn(AnimeList);

        var Animes = repository.findByName(null);
        Assertions.assertThat(Animes).isNotNull().isEmpty();

    }

    @Test
    @DisplayName("findByName returns list with found object when name exists")
    void findByName_ReturnsFoundAnimeInList_WhenNameIsFound() {
        BDDMockito.when(AnimeData.getAnimes()).thenReturn(AnimeList);

        var expectedAnime = AnimeList.getFirst();
        var Animes = repository.findByName(expectedAnime.getName());
        Assertions.assertThat(Animes).hasSize(1).contains(expectedAnime);
    }

    @Test
    @DisplayName("save creates an Anime")
    @Order(5)
    void save_CreatesAnime_WhenSuccessful() {
        BDDMockito.when(AnimeData.getAnimes()).thenReturn(AnimeList);

        var AnimeToSave = Anime.builder().id(99L)
                .name("Gachiakuta").build();
        var Anime = repository.save(AnimeToSave);

        Assertions.assertThat(Anime).isEqualTo(AnimeToSave);

        var animeSavedOptional = repository.findById(AnimeToSave.getId());
        Assertions.assertThat(animeSavedOptional).isPresent().contains(AnimeToSave);
    }

    @Test
    @DisplayName("delete removes an Anime")
    @Order(6)
    void delete_RemovesAnime_WhenSuccessful() {
        BDDMockito.when(AnimeData.getAnimes()).thenReturn(AnimeList);

        var AnimeToDelete = AnimeList.getFirst();
        repository.delete(AnimeToDelete);
        var Animes = repository.findAll();
        Assertions.assertThat(Animes).isNotEmpty().doesNotContain(AnimeToDelete);

    }

    @Test
    @DisplayName("update updates an Anime")
    @Order(7)
    void update_UpdatesAnime_WhenSuccessful() {
        BDDMockito.when(AnimeData.getAnimes()).thenReturn(AnimeList);

        var AnimeToUpdate = this.AnimeList.getFirst();
        AnimeToUpdate.setName("Solo Leveling");

        repository.update(AnimeToUpdate);

        Assertions.assertThat(this.AnimeList).contains(AnimeToUpdate);
        var AnimeUpdatedOptional = repository.findById(AnimeToUpdate.getId());
        Assertions.assertThat(AnimeUpdatedOptional).isPresent();
        Assertions.assertThat(AnimeUpdatedOptional.get().getName()).isEqualTo(AnimeToUpdate.getName());
    }
}