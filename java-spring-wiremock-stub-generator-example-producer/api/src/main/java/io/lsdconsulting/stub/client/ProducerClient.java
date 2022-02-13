package io.lsdconsulting.stub.client;

import io.lsdconsulting.stub.api.response.ProducerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "producerClient", url = "${producer.url}")
public interface ProducerClient {

    @GetMapping("/producerResource/123")
    ProducerResponse getProducerData();

}
