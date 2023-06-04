package ru.gloomyana.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gloomyana.models.BookingResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.gloomyana.specs.RestfulBookerSpec.*;

@Epic("API tests for restful-booker")
@Feature("Get booking")
@Tag("api")
@Owner("gloomyana")
public class GetBookingTests {
    @Test
    @DisplayName("Get all booking ids returns status 200")
    public void getAllBookingIdsReturns200() {
        step("Make get all booking request and verify it returns status 200", () ->
                given(baseRequestSpec)
                        .when()
                        .get("/booking")
                        .then()
                        .assertThat().statusCode(200));
    }

    @Test
    @DisplayName("Get booking request returns not null data")
    public void getBookingReturnsNotNullData() {
        BookingResponseModel response = step("Make get data request by id", () ->
                given(baseRequestSpec)
                        .contentType(JSON)
                        .when()
                        .get("booking/8")
                        .then()
                        .statusCode(200)
                        .spec(bookingResponseSpec)
                        .extract().as(BookingResponseModel.class));
        step("Verify successful get data request", () ->
                assertThat(response.getLastname()).isNotNull());
    }
}
