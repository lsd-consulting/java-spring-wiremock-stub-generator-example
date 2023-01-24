package io.lsdconsulting.stub.app.controller;

import io.lsdconsulting.stub.api.response.Response;
import io.lsdconsulting.stub.api.response.ServerResponse;
import io.lsdconsulting.stub.client.ServerClient;
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
public class ClientController {

    private final ServerClient serverClient;

    @GetMapping("/{id}")
    public Response getData(@PathVariable final String id) {
        ServerResponse serverResponse = serverClient.getServerData();
        return Response.builder()
                .id(serverResponse.getId())
                .name(serverResponse.getAuthor().getName())
                .build();
    }

}
