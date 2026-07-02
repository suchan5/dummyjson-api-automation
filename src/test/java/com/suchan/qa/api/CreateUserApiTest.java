package com.suchan.qa.api;

import com.suchan.qa.base.BaseTest;
import com.suchan.qa.dto.CreateUserRequest;
import com.suchan.qa.dto.CreateUserResponse;
import com.suchan.qa.utils.ConfigReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserApiTest extends BaseTest {
    @Test
    void createUserTest() {
        System.out.println("Hello Junit!");

        // DTO Builder 사용 (롬복의 @Builder)
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .name("Su")
                .job("QA Engineer")
                .build();

       CreateUserResponse createUserResponse =  given()
                .contentType(ContentType.JSON)
                .header("x-api-key", ConfigReader.getProperty("api.key")) // ConfigReader클래스에 깃에 올라가면 안되는 정보 저장
                .body(createUserRequest) // request body

                .when()
                    .post("/api/users")

                .then().statusCode(201)
                .extract()
                .as(CreateUserResponse.class); // response body

            assertEquals("Su", createUserResponse.getName());
            assertEquals("QA Engineer", createUserResponse.getJob());
    }
}
