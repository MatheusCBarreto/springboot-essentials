package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.repository.AnimeRepository;
import academy.devdojo.springboot2.requests.AnimePostResquestBody;
import academy.devdojo.springboot2.requests.AnimePutResquestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(Long id) {
        return animeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime ID not found"));
    }

    public Anime save(AnimePostResquestBody animePostResquestBody) {
        Anime anime = Anime.builder().name(animePostResquestBody.getName()).build();
        return animeRepository.save(anime);
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutResquestBody animePutResquestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutResquestBody.getId());
        Anime anime = Anime.builder().id(savedAnime.getId()).name(animePutResquestBody.getName()).build();

        animeRepository.save(anime);
    }
}
