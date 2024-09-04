package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Anime Repository.")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    private Anime createAnime() {
        return Anime.builder()
                .name("Hajime no ippo")
                .build();
    }

    @Test
    @DisplayName("Save creates anime when successful")
    void save_PersistAnime_WhenSuccessful() {
        Anime anime = createAnime();
        Anime savedAnime = this.animeRepository.save(anime);

        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(anime.getName());
    }

    @Test
    @DisplayName("Find by name returns list of anime when successful")
    void findByName_ReturnListOfAnime_WhenSuccessful() {
        Anime anime = createAnime();
        Anime savedAnime = this.animeRepository.save(anime);

        String name = savedAnime.getName();

        List<Anime> animeList = animeRepository.findByName(name);

        Assertions.assertThat(animeList).isNotEmpty();
        Assertions.assertThat(animeList).contains(savedAnime);
    }

    @Test
    @DisplayName("Find by name returns empty list of anime when anime not found")
    void findByName_ReturnListEmpty_WhenNotFound() {
        List<Anime> animeList = animeRepository.findByName("Bob Esponja");

        Assertions.assertThat(animeList).isEmpty();
    }

    @Test
    @DisplayName("Save update anime when successful")
    void save_UpdateAnime_WhenSuccessful() {
        Anime anime = createAnime();
        Anime savedAnime = this.animeRepository.save(anime);

        savedAnime.setName("OverLord");

        Anime animeUpdated = this.animeRepository.save(savedAnime);

        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(savedAnime.getName());
    }

    @Test
    @DisplayName("Save delete anime when successful")
    void delete_DeleteAnime_WhenSuccessful() {
        Anime anime = createAnime();
        Anime savedAnime = this.animeRepository.save(anime);

        this.animeRepository.delete(savedAnime);

        this.animeRepository.findById(savedAnime.getId());

        Optional<Anime> animeOptional = this.animeRepository.findById(savedAnime.getId());

        Assertions.assertThat(animeOptional.isEmpty()).isTrue();
    }
}