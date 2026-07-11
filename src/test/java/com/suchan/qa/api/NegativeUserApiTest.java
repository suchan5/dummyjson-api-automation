package com.suchan.qa.api;

import com.suchan.qa.base.BaseTest;
import com.suchan.qa.dto.ErrorResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

public class NegativeUserApiTest extends BaseTest {

    @Test
    @DisplayName("Should return 404 when requesting a non-existing user")
    void getNonExistingUserShouldReturn404() {
        ErrorResponse errorResponse = given()
                .pathParam("id", 999999)

                .when()
                .get("/users/{id}")

                .then()
                .statusCode(404)
                .log().all()
                .extract()
                .as(ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains("not found"));
    }

    @Test
    @DisplayName("Should return 401 when requesting the current user without a token")
    void getCurrentUserWithoutTokenShouldReturn401 () {
        ErrorResponse errorResponse = given()
                .when()
                .get("/auth/me")

                .then()
                .statusCode(401)
                .log().all()
                .extract()
                .as(ErrorResponse.class);

        assertEquals( "Access Token is required", errorResponse.getMessage());

    }

    @Test
    @DisplayName("Should return 401 when requesting the current user with an invalid token")
    void getCurrentUserWithInvalidTokenShouldReturn401() {
        String invalidToken = "invalidToken";
        ErrorResponse errorResponse = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + invalidToken)

                .when()
                .get("/auth/me")

                .then()
                .statusCode(401)
                .log().all()
                .extract()
                .as(ErrorResponse.class);

        assertEquals("Invalid/Expired Token!", errorResponse.getMessage());

    }
}
