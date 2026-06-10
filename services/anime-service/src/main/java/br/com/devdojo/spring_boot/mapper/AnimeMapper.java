package br.com.devdojo.spring_boot.mapper;

import br.com.devdojo.spring_boot.domain.Anime;
import br.com.devdojo.spring_boot.response.AnimeGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnimeMapper {

    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    /*@Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Anime toAnime(AnimePostRequest postRequest);*/

    AnimeGetResponse toAnimeGetResponse(Anime anime);
}
