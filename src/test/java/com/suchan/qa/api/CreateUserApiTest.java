package com.suchan.qa.api;

import com.suchan.qa.base.BaseTest;
import com.suchan.qa.dto.CreateUserRequest;
import com.suchan.qa.dto.CreateUserResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserApiTest extends BaseTest {
    @Test
    void createUserTest() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .firstName("Su Chan")
                .lastName("Kim")
                .age(55)
                .build();

       CreateUserResponse createUserResponse =  given()
                .contentType(ContentType.JSON)
                .body(createUserRequest) // request body
               .log().all()

                .when()
                    .post("/users/add")

                .then().statusCode(201)
                .log().all()
                .extract()
                .as(CreateUserResponse.class); // response body


            assertEquals("Su Chan", createUserResponse.getFirstName());
            assertEquals("Kim", createUserResponse.getLastName());
            assertEquals(55, createUserResponse.getAge());
    }
}
