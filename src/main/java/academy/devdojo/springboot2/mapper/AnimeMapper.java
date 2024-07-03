package academy.devdojo.springboot2.mapper;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.requests.AnimePostResquestBody;
import academy.devdojo.springboot2.requests.AnimePutResquestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostResquestBody animePostResquestBody);

    public abstract Anime toAnime(AnimePutResquestBody animePutResquestBody);
}
