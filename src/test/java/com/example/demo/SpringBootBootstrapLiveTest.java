package com.example.demo;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SpringBootBootstrapLiveTest {
    
    private static final String API_ROOT = "http://localhost:8081/rooms";

    @Test
    public void whenGetAllRooms_thenOK() {
        final Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetRoomsByName_thenOK()
     {
        final Room room = createRandomRoom();
        createRoomAsUri(room);

        final Response response = RestAssured.get(API_ROOT + "/name/" + room.getName());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.as(List.class)
            .size() > 0);
    }

    @Test
    public void whenGetCreatedRoomById_thenOK() {
        final Room room = createRandomRoom();
        final String location = createRoomAsUri(room);

        final Response response = RestAssured.get(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(room.getName(), response.jsonPath()
            .get("name"));
    }

    @Test
    public void whenGetNotExistRoomById_thenNotFound() {
        final Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    // POST
    @Test
    public void whenCreateNewRoom_thenCreated() {
        final Room room = createRandomRoom();

        final Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(room)
            .post(API_ROOT);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

 /** Validation of empthy decription is mnot implemented   @Test
    public void whenInvalidroom_thenError() {
        final Room room = createRandomRoom();
        room.setDesciption(null);

        final Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(room)
            .post(API_ROOT);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }
*/
    @Test
    public void whenUpdateCreatedRoom_thenUpdated() {
        final Room room = createRandomRoom();
        final String location = createRoomAsUri(room);

        room.setId(Long.parseLong(location.split("api/rooms/")[1]));
        room.setDescription("newAuthor");
        Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(room)
            .put(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("newAuthor", response.jsonPath()
            .get("author"));

    }

    @Test
    public void whenDeleteCreatedRoom_thenOk() {
        final Room room = createRandomRoom();
        final String location = createRoomAsUri(room);

        Response response = RestAssured.delete(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }
    

    private Room createRandomRoom() {
        Room room = new Room();
        room.setId(1);
        room.setName(randomAlphabetic(10));
        room.setDescription(randomAlphabetic(15));
        return room;
    }

    private String createRoomAsUri(Room room) {
        Response response = RestAssured.given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(room)
          .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath().get("id");
    }
}