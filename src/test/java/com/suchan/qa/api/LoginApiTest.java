package com.suchan.qa.api;

import com.suchan.qa.base.BaseTest;
import com.suchan.qa.dto.LoginRequest;
import com.suchan.qa.dto.LoginResponse;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginApiTest extends BaseTest {
    @Test
    void loginTest() {
        System.out.println("Hello Junit!");

        LoginRequest loginRequest = LoginRequest.builder()
                .username("emilys")
                .password("emilyspass")
                .build();

        LoginResponse loginResponse =  given()
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

        String token = loginResponse.getAccessToken();

        assertNotNull(token);
        assertFalse(token.isEmpty());
        System.out.println(token);
    }
}
