package com.suchan.qa.api;

import com.suchan.qa.base.BaseTest;
import com.suchan.qa.dto.CreateUserRequest;
import com.suchan.qa.utils.ConfigReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserApiTest extends BaseTest {
    @Test
    void createUserTest() {

        // DTO Builder 사용 (롬복의 @Builder)
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .name("Su")
                .job("QA Engineer")
                .build();

        given()
                .contentType(ContentType.JSON)
                .header("x-api-key", ConfigReader.getProperty("api.key")) // ConfigReader클래스에 깃에 올라가면 안되는 정보 저장
                //request body
                .body(createUserRequest)

                .when()
                    .post("/api/users")

                .then().statusCode(201)
                    // response body
                    .body("name", equalTo("Su"))
                    .body("job", equalTo("QA Engineer"))
                    .log().all();

    }
}
