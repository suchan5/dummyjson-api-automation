package com.suchan.qa.api;

import com.suchan.qa.base.BaseTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class FirstApiTest extends BaseTest {
    @Test
    void firstTest() {
        System.out.println("Hello Junit!");

//        given()
//                .when()
//                // Uses JSONPlaceholder. "https://jsonplaceholder.typicode.com"
//                // Disabled because current BaseTest points to ReqRes.
//                .get("/posts/1")
//
//                .then()
//                .statusCode(200)
//                .body("userId", equalTo(1))
//                .body("id", equalTo(1))
//                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
//                .body("body", notNullValue())
//                .log().all();

    }
}
