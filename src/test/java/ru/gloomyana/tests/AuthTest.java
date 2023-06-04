package ru.gloomyana.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gloomyana.models.AuthRequestModel;
import ru.gloomyana.models.AuthResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.gloomyana.specs.RestfulBookerSpec.authResponseSpec;
import static ru.gloomyana.specs.RestfulBookerSpec.baseRequestSpec;

@Epic("API tests for restful-booker")
@Feature("Auth token")
@Tag("api")
@Owner("gloomyana")
public class AuthTest {
    @Test
    @DisplayName("Successful create a new auth token")
    public void createAuthToken() {
        AuthRequestModel authRequestModel = new AuthRequestModel();
        TestData testData = new TestData();

        authRequestModel.setUsername(testData.username);
        authRequestModel.setPassword(testData.password);

        AuthResponseModel response = step("Make token request with user data", () ->
                given(baseRequestSpec)
                        .body(authRequestModel)
                        .contentType(JSON)
                        .when()
                        .post("/auth")
                        .then()
                        .statusCode(200)
                        .spec(authResponseSpec)
                        .extract().as(AuthResponseModel.class));
        step("Verify successful create token", () ->
                assertThat(response.getToken()).isNotEmpty());
    }
}
