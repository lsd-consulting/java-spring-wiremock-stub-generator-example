package io.lsdconsulting.stub.app.controller;

import io.lsdconsulting.stub.api.response.Author;
import io.lsdconsulting.stub.api.response.ProducerResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.time.Instant.EPOCH;

@Validated
@RestController("/producerResource")
public class ProducerController {

    @GetMapping("/123")
    public ProducerResponse getData() {
        return ProducerResponse.builder()
                .id("someId")
                .author(Author.builder().name("author").build())
                .message("message")
                .created(ZonedDateTime.ofInstant(EPOCH, ZoneId.systemDefault()))
                .build();
    }
}
