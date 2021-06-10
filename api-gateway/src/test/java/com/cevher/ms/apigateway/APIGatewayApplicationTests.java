package com.cevher.ms.apigateway;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class APIGatewayApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Ignore("not yet ready , Please ignore.")
    @Test
    void fallBackPerson() throws Exception {
        assertThat(this.restTemplate
                .getForObject("http://127.0.0.1:" + port + "/personServiceFallBack", String.class)
        ).contains("FallBack Person Controller");
    }

}
