package io.lsdconsulting.stub.client;

import io.lsdconsulting.stub.api.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client", url = "${client.url}")
public interface Client {

    @GetMapping("/resource/{id}")
    Response getData(@PathVariable(name = "id") String id);
}
