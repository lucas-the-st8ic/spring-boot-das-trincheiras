package br.com.devdojo.spring_boot.mapper;

import br.com.devdojo.spring_boot.domain.Producer;
import br.com.devdojo.spring_boot.request.ProducerPostRequest;
import br.com.devdojo.spring_boot.request.ProducerPutRequest;
import br.com.devdojo.spring_boot.response.ProducerGetResponse;
import br.com.devdojo.spring_boot.response.ProducerPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProducerMapper {
    
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Producer toProducer(ProducerPostRequest postRequest);

    Producer toProducer(ProducerPutRequest request);

    ProducerGetResponse toproducerGetResponse(Producer producer);

    ProducerPostResponse toproducerPostResponse(Producer producer);

    List<ProducerGetResponse> toProducerGetResponseList(List<Producer> producers);

}
