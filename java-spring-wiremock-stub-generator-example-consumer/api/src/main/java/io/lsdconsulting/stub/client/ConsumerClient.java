package io.lsdconsulting.stub.client;

import io.lsdconsulting.stub.api.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "consumerClient", url = "${consumer.url}")
public interface ConsumerClient {

    @GetMapping("/resource/{id}")
    Response getData(@PathVariable(name = "id") String id);
}
