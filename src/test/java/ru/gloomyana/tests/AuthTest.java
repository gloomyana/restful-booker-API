package ru.gloomyana.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gloomyana.config.AuthConfig;
import ru.gloomyana.models.AuthRequestModel;
import ru.gloomyana.models.AuthResponseModel;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.gloomyana.helpers.ApiHelpers.createToken;

@Epic("API tests for restful-booker")
@Feature("Auth token")
@Tag("api")
@Owner("gloomyana")
public class AuthTest {

    @Test
    @DisplayName("Successful create a new auth token")
    public void createAuthToken() {
        AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
        AuthRequestModel authRequestModel = new AuthRequestModel(config.username(), config.password());

        AuthResponseModel response = step("Make token request with user data", () ->
                createToken(authRequestModel));
        step("Verify successful create token", () ->
                assertThat(response.getToken()).isNotNull());
    }
}
