package com.library;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public abstract class BaseTest {

    private static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("library")
                    .withUsername("test")
                    .withPassword("test")
                    .withReuse(true);

    @BeforeAll
    static void setupContainer() {
        if (!POSTGRESQL_CONTAINER.isRunning()) {
            POSTGRESQL_CONTAINER.start();
        }

        System.setProperty("spring.datasource.url", POSTGRESQL_CONTAINER.getJdbcUrl());
        System.setProperty("spring.datasource.username", POSTGRESQL_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password", POSTGRESQL_CONTAINER.getPassword());
    }
}
