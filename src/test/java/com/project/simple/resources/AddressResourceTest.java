package com.project.simple.resources;

import com.project.simple.entities.Address;
import com.project.simple.entities.Client;
import com.project.simple.services.AddressService;
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
class AddressResourceTest {

    @LocalServerPort
    private int randomPort;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AddressService addressService;

    @BeforeEach //ANOTAÇÃO JUNIT
    public void setUpTest() {
        System.out.println(randomPort);
        RestAssured.port = randomPort;
    }

    @Test
    void WhenFindByClientCheckResult() {
        Client cli = new Client(null, "Luiz", new Date());
        Address ad1 = new Address(null, "Rua das conchas", 91561958, 100, "Guarujá", cli, 'n');
        cli = clientService.insert(cli);
        ad1 = addressService.insert(ad1, cli.getId());
        RestAssured.given()
                .when()
                .get("/address/{id}", cli.getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void whenCreateThenCheckIsCreated() {
        Client cli = new Client(null, "Luiz", new Date());
        cli = clientService.insert(cli);
        Address ad1 = new Address(null, "Rua das conchas", 91561958, 100, "Guarujá", cli, 'n');
        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(ad1)
                .post("/address/{id}", cli.getId())
                .then()
                .statusCode(201);

    }

    @Test
    void WhenDeleteByIdCheckResult() {
        Client cli = new Client(null, "Luiz", new Date());
        Address ad1 = new Address(null, "Rua das conchas", 91561958, 100, "Guarujá", cli, 'n');
        cli = clientService.insert(cli);
        ad1 = addressService.insert(ad1, cli.getId());
        RestAssured.given()
                .when()
                .delete("/address/{id}", ad1.getId())
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void WheUpdateByIdCheckResult() {
        Client cli = new Client(null, "Luiz", new Date());
        Address ad1 = new Address(null, "Rua das conchas", 91561958, 100, "Guarujá", cli, 'n');
        cli = clientService.insert(cli);
        ad1 = addressService.insert(ad1, cli.getId());
        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .put("/address/{idClient}/{idAddress}", cli.getId(), ad1.getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
