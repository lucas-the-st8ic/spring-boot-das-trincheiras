package br.com.devdojo.spring_boot.mapper;

import br.com.devdojo.spring_boot.domain.Producer;
import br.com.devdojo.spring_boot.request.ProducerPostRequest;
import br.com.devdojo.spring_boot.response.ProducerGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProducerMapper {

    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Producer toProducer(ProducerPostRequest postRequest);

    ProducerGetResponse toproducerGetResponse(Producer producer);
}
