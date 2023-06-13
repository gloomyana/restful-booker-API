package ru.gloomyana.tests;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.gloomyana.config.AuthConfig;
import ru.gloomyana.models.AuthRequestModel;
import ru.gloomyana.models.AuthResponseModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static ru.gloomyana.specs.RestfulBookerSpec.authResponseSpec;
import static ru.gloomyana.specs.RestfulBookerSpec.baseRequestSpec;

public class TestBase {
    protected String token;
    TestData testData = new TestData();
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
        AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
        authRequestModel.setUsername(config.username());
        authRequestModel.setPassword(config.password());

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