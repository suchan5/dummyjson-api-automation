package com.suchan.qa.api;

import com.suchan.qa.base.BaseTest;
import com.suchan.qa.dto.LoginRequest;
import com.suchan.qa.dto.LoginResponse;
import com.suchan.qa.utils.ConfigReader;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class LoginApiTest extends BaseTest {
    @Test
    void loginTest() {
        System.out.println("Hello Junit!");

        LoginRequest loginRequest = LoginRequest.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();

        LoginResponse loginResponse =  given()
                .contentType(ContentType.JSON)
                .header("x-api-key", ConfigReader.getProperty("api.key"))
                .body(loginRequest)

                .when()
                .post("/api/login")

                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .as(LoginResponse.class);

    }
}
