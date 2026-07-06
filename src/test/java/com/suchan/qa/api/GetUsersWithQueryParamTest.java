package com.suchan.qa.api;

import com.suchan.qa.base.BaseTest;
import com.suchan.qa.dto.CurrentUserResponse;
import com.suchan.qa.dto.UsersResponse;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class GetUsersWithQueryParamTest extends BaseTest {
    @Test
    void getUserWithQueryParam() {
        UsersResponse usersResponse = given()
                .queryParam("limit", 3)
                .queryParam("skip", 20)
                .queryParam("sortBy", "age")
                .queryParam("order", "desc")

                .when()
                .get("/users")

                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .as(UsersResponse.class);

        assertEquals(112, usersResponse.getUsers().get(0).getId());
        assertEquals(3, usersResponse.getLimit());
        assertEquals(3, usersResponse.getUsers().size());

    }
}
