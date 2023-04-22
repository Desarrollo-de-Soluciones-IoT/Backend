package com.docseeker.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckHttpResponse {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    // checks that the response from the backend is "Hello World from DocSeeker Backend Application!" when calling the root URL of the application
    @Test
    public void shouldPassIfStringMatches() {
            assertEquals("Hello World from DocSeeker Backend Application!", this.testRestTemplate.getForObject("http://localhost:" + port + "/", String.class));
    }
}
