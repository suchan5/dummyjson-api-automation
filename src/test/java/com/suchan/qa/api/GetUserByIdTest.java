package com.suchan.qa.api;

import com.suchan.qa.base.BaseTest;
import com.suchan.qa.dto.CurrentUserResponse;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserByIdTest extends BaseTest {
   @Test
    void getUserById() {
        CurrentUserResponse currentUserResponse = given()
                .pathParam("id", 5)

                .when()
                .get("/users/{id}")

                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .as(CurrentUserResponse.class);

        assertEquals(5, currentUserResponse.getId());
        assertEquals("Emma", currentUserResponse.getFirstName());
        assertEquals("Miller", currentUserResponse.getLastName());


    }
}
