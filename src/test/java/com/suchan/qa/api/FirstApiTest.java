package com.suchan.qa.api;

import com.suchan.qa.base.BaseTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class FirstApiTest extends BaseTest {
    @Test
    void firstTest() {
        System.out.println("Hello Junit!");

        given()
                .log().all() // request log

                .when()
                .get("/users/1")

                .then()
                .statusCode(200)
                .log().all() //response log
                .body("id", equalTo(1))
                .body("firstName", equalTo("Emily"))
                .body("company.address.postalCode", equalTo("37657"));


    }
}
