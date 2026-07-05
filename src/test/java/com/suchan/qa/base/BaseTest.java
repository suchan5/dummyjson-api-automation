package com.suchan.qa.base;

import com.suchan.qa.utils.ConfigReader;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = ConfigReader.getProperty("base.url");
    }
}
