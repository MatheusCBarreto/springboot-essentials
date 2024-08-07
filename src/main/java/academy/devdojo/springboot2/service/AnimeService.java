package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.exception.BadRequestException;
import academy.devdojo.springboot2.mapper.AnimeMapper;
import academy.devdojo.springboot2.repository.AnimeRepository;
import academy.devdojo.springboot2.requests.AnimePostResquestBody;
import academy.devdojo.springboot2.requests.AnimePutResquestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public List<Anime> listAllNonPageable() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(Long id) {
        return animeRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("Anime ID not found"));
    }
    @Transactional
    public Anime save(AnimePostResquestBody animePostResquestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostResquestBody));
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutResquestBody animePutResquestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutResquestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutResquestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }

}
