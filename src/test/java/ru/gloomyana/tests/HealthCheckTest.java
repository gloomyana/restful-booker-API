package ru.gloomyana.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static ru.gloomyana.specs.RestfulBookerSpec.baseRequestSpec;

@Epic("API tests for restful-booker")
@Feature("API health check")
@Tag("api")
@Owner("gloomyana")
public class HealthCheckTest {

    @Test
    @DisplayName("Health check endpoint to confirm the API is up")
    public void healthCheckReturns201() {
        step("Make health check request and verify it returns status 201", () ->
                given(baseRequestSpec)
                        .when()
                        .get("/ping")
                        .then()
                        .assertThat().statusCode(201));
    }
}
