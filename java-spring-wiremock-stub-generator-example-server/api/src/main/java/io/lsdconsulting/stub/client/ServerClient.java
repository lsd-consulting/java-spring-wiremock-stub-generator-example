package io.lsdconsulting.stub.client;

import io.lsdconsulting.stub.api.response.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "serverClient", url = "${server.url}")
public interface ServerClient {

    @GetMapping("/serverResource/123")
    ServerResponse getServerData();

}
