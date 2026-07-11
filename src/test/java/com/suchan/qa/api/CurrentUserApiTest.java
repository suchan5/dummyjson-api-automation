package com.suchan.qa.api;

import com.suchan.qa.base.BaseTest;
import com.suchan.qa.dto.CurrentUserResponse;
import com.suchan.qa.dto.LoginRequest;
import com.suchan.qa.dto.LoginResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.equalTo;

public class CurrentUserApiTest extends BaseTest {

    @Test
    void currentUserTest() {
        // Authentication: login
        LoginRequest loginRequest = LoginRequest.builder()
                .username("emilys")
                .password("emilyspass")
                .build();

        LoginResponse loginResponse = given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .log().all()

                .when()
                .post("/auth/login")

                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .as(LoginResponse.class);

        // Extract token
        String token = loginResponse.getAccessToken();

        assertNotNull(token);
        assertFalse(token.isBlank());
        System.out.println(token);

        // Authorization: call protected API
        CurrentUserResponse currentUserResponse = given()
                .header("Authorization", "Bearer " + token)

                .when()
                .get("/auth/me")

                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .as(CurrentUserResponse.class);

        // Assert
        assertEquals("emilys", currentUserResponse.getUsername());
        assertEquals("Emily", currentUserResponse.getFirstName());
        assertNotNull(currentUserResponse.getEmail());

        // API Chaning: userId추출
        int userId = currentUserResponse.getId();
        assertEquals(1, userId);

        // userId를 다른 API호출에 전달 : getUserById
        CurrentUserResponse user = given()

                .when()
                .get("/users/" + userId)

                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .as(CurrentUserResponse.class);

        assertEquals(userId, user.getId());

    }

    @Test
    void getCurrentUserWithoutTokenTest() {
        given()
                .contentType(ContentType.JSON)
                .log().all()

                .when()
                .get("/auth/me")

                .then()
                .statusCode(401)
                .body("message", equalTo("Access Token is required"))
                .log().all();
    }
}