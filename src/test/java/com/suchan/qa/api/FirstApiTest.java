package com.suchan.qa.api;

import com.suchan.qa.base.BaseTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class FirstApiTest extends BaseTest {
    @Test
    void firstTest() {
        System.out.println("Hello Junit!");
        given()
                .log().all()
                .when()
                .get("/posts/1")
                .then()
                .log().all()
                .statusCode(200);

    }
}
