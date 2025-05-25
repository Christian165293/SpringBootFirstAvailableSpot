package com.example.demo;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MoveControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("GET /computer without parameters should return Space with null")
    void whenNoSpacesProvided_shouldReturnNullSpace() {
        given()
                .port(port)
                .when()
                .get("/computer")
                .then()
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("space", equalTo(null));
    }

    @Test
    @DisplayName("GET /computer with empty spaces parameter should return Space(1)")
    void whenEmptySpacesProvided_shouldReturnFirstSpace() {
        given()
                .port(port)
                .when()
                .get("/computer?spaces=")
                .then()
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("space", equalTo(1));
    }

    @Test
    @DisplayName("GET /computer with single space=1 should return Space(2)")
    void whenOnlySpace1Taken_shouldReturnSpace2() {
        given()
                .port(port)
                .when()
                .get("/computer?spaces=1")
                .then()
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("space", equalTo(2));
    }

    @Test
    @DisplayName("GET /computer with spaces=1,3,7 should return Space(2)")
    void whenSpaces1_3_7Taken_shouldReturnSpace2() {
        given()
                .port(port)
                .when()
                .get("/computer?spaces=1,3,7")
                .then()
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("space", equalTo(2));
    }

    @Test
    @DisplayName("GET /computer with other spaces should return Space(444)")
    void whenDefaultCase_shouldReturnSpace444() {
        given()
                .port(port)
                .when()
                .get("/computer?spaces=2,4,6,8")
                .then()
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("space", equalTo(444));
    }
}
