package ru.gloomyana.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.gloomyana.models.AuthRequestModel;
import ru.gloomyana.models.AuthResponseModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static ru.gloomyana.specs.RestfulBookerSpec.authResponseSpec;
import static ru.gloomyana.specs.RestfulBookerSpec.baseRequestSpec;

public class TestBase {
    protected String token;

    @BeforeAll
    static void checkHealthBeforeTest() {
        given(baseRequestSpec)
                .when()
                .get("/ping")
                .then()
                .assertThat().statusCode(201);
    }

    @BeforeEach
    void createAuthToken() {
        AuthRequestModel authRequestModel = new AuthRequestModel();
        TestData testData = new TestData();
        authRequestModel.setUsername(testData.username);
        authRequestModel.setPassword(testData.password);

        AuthResponseModel response = given(baseRequestSpec)
                .body(authRequestModel)
                .contentType(JSON)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .spec(authResponseSpec)
                .extract().as(AuthResponseModel.class);
        token = response.getToken();
    }
}