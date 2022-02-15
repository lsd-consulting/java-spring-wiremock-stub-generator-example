package io.lsdconsulting.stub.app.controller;

import io.lsdconsulting.stub.api.response.Author;
import io.lsdconsulting.stub.api.response.ServerResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.time.Instant.EPOCH;

@Validated
@RestController("/serverResource")
public class ServerController {

    @GetMapping("/123")
    public ServerResponse getData() {
        return ServerResponse.builder()
                .id("someId")
                .author(Author.builder().name("author").build())
                .message("message")
                .created(ZonedDateTime.ofInstant(EPOCH, ZoneId.systemDefault()))
                .build();
    }
}
