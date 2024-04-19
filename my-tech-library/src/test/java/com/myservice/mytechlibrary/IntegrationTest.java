package com.myservice.mytechlibrary;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Slf4j
@SpringBootTest(classes = SpringBootApplicationTestConfiguration.class, webEnvironment = RANDOM_PORT)
class IntegrationTest {

    @Test
    void shouldStart() {
        log.info("Context is up");
    }

}
