package com.suchan.qa.api;

import com.suchan.qa.base.BaseTest;
import com.suchan.qa.dto.ErrorResponse;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class NegativeGetUserApiTest extends BaseTest {
    @Test
    void negativeUserTest() {
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
}
