package com.project.simple.resources;

import com.project.simple.entities.Client;
import com.project.simple.repositories.ClientRepository;
import com.project.simple.services.ClientService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientResourceTest {

    @LocalServerPort
    private int randomPort;
    @Autowired
    private ClientService clientService;


    @BeforeEach //ANOTAÇÃO JUNIT
    public void setUpTest() {
        System.out.println(randomPort);
        RestAssured.port = randomPort;
    }

    @Test
    void WhenFindByIdCheckResult() {
        Client cli = new Client(null, "Luiz", new Date());
        cli = clientService.insert(cli);
        RestAssured.given()
                .when()
                .get("/client/{id}", cli.getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void whenCreateThenCheckIsCreated() {
        Client client1 = new Client(null, "Luiz", new Date());
        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(client1)
                .post("/client")
                .then()
                .statusCode(201);

    }

    @Test
    void WhenDeleteByIdCheckResult() {
        Client cli = new Client(null, "Luiz", new Date());
        cli = clientService.insert(cli);
        RestAssured.given()
                .when()
                .delete("/client/{id}", cli.getId())
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void WheUpdateByIdCheckResult() {
        Client cli = new Client(null, "Luiz", new Date());
        Client cli2 = new Client(null, "Rafa", new Date());
        cli = clientService.insert(cli);
        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(cli2)
                .put("/client/{id}", cli.getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}