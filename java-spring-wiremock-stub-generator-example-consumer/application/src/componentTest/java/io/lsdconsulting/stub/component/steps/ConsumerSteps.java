package io.lsdconsulting.stub.component.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import feign.FeignException;
import io.cucumber.java8.En;
import io.cucumber.spring.CucumberContextConfiguration;
import io.lsdconsulting.stub.api.response.Author;
import io.lsdconsulting.stub.api.response.ProducerResponse;
import io.lsdconsulting.stub.api.response.Response;
import io.lsdconsulting.stub.app.ConsumerApplication;
import io.lsdconsulting.stub.app.controller.ProducerControllerStub;
import io.lsdconsulting.stub.client.ConsumerClient;
import io.lsdconsulting.stub.component.config.WiremockConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@SpringBootTest(webEnvironment = DEFINED_PORT, classes = {ConsumerApplication.class})
@CucumberContextConfiguration
@ActiveProfiles("test")
@Import({WiremockConfiguration.class})
@EnableFeignClients(clients = ConsumerClient.class)
public class ConsumerSteps implements En {
    private final String name = randomAlphabetic(10);
    private final Integer age = nextInt(0, 125);

    private final ProducerControllerStub producerControllerStub = new ProducerControllerStub(new ObjectMapper());

    private Response response;
    private HttpStatus httpStatus;

    public ConsumerSteps(ConsumerClient consumerClient, WireMockServer wireMockServer) {
        Before(() -> {
            wireMockServer.start();
            configureFor("localhost", wireMockServer.port());
            wireMockServer.resetAll();
        });
        After(wireMockServer::stop);

        Given("^the consumer is ready to accept requests$", () ->
                producerControllerStub.getGetData(ProducerResponse.builder().id("id").author(Author.builder().name("author").build()).build()));

        When("^a request is sent to the consumer$", () -> {
            try {
                response = consumerClient.getData("someId");
            } catch (final FeignException e) {
                httpStatus = HttpStatus.valueOf(e.status());
            }
        });

        Then("^the data from the producer's stub is returned$", () -> {
            Response expectedResponse = Response.builder()
                    .id("id")
                    .name("author")
                    .build();

            assertThat(response, equalTo(expectedResponse));
        });

        Then("^the HTTP status code NOT_FOUND is returned$", () -> assertThat(httpStatus, equalTo(INTERNAL_SERVER_ERROR)));

    }
}
