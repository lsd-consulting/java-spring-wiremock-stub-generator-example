package io.lsdconsulting.stub.app.controller;

import io.lsdconsulting.stub.api.response.ProducerResponse;
import io.lsdconsulting.stub.api.response.Response;
import io.lsdconsulting.stub.client.ProducerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "/resource")
@RequiredArgsConstructor
public class ConsumerController {

    private final ProducerClient producerClient;

    @GetMapping("/{id}")
    public Response getData(@PathVariable final String id) {
        ProducerResponse producerResponse = producerClient.getProducerData();
        return Response.builder()
                .id(producerResponse.getId())
                .name(producerResponse.getAuthor().getName())
                .build();
    }

}
