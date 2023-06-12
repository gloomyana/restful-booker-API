package ru.gloomyana.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gloomyana.models.BookingRequestModel;
import ru.gloomyana.models.CreateBookingResponseModel;
import ru.gloomyana.models.BookingResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.gloomyana.specs.RestfulBookerSpec.*;

@Epic("API tests for restful-booker")
@Feature("Create and update booking")
@Tag("api")
@Owner("gloomyana")
public class CreateAndUpdateBookingTests extends TestBase {

    @Test
    @DisplayName("Successful create a new booking")
    public void successfulCreateNewBooking() {
        TestData testData = new TestData();
        BookingRequestModel bookingRequestModel = testData.createBookingRequestModel();

        CreateBookingResponseModel response = step("Make create booking request", () ->
                given(baseRequestSpec)
                        .header("Cookie", "token=" + token)
                        .body(bookingRequestModel)
                        .contentType(JSON)
                        .when()
                        .post("/booking")
                        .then()
                        .statusCode(200)
                        .spec(createBookingResponseSpec)
                        .extract().as(CreateBookingResponseModel.class));
        step("Verify successful create new booking id", () ->
                assertThat(response.getBookingId()).isNotNull());
        step("Verify successful create new booking with request data", () ->
                assertThat(response.getBookingRequestModel().equals(bookingRequestModel)));
    }

    @Test
    @DisplayName("Successful update booking data by id")
    public void successfulUpdateBooking() {
        TestData testData = new TestData();
        BookingRequestModel bookingRequestModel = testData.createBookingRequestModel();

        BookingResponseModel response = step("Make update all booking data request", () ->
                given(baseRequestSpec)
                        .header("Cookie", "token=" + token)
                        .body(bookingRequestModel)
                        .contentType(JSON)
                        .when()
                        .put("booking/7")
                        .then()
                        .statusCode(200)
                        .spec(bookingResponseSpec)
                        .extract().as(BookingResponseModel.class));
        step("Verify successful update firstname", () ->
                assertThat(response.getFirstname()).isEqualTo(bookingRequestModel.getFirstname()));
        step("Verify successful update lastname", () ->
                assertThat(response.getLastname()).isEqualTo(bookingRequestModel.getLastname()));
        step("Verify successful update total price", () ->
                assertThat(response.getTotalPrice()).isEqualTo(bookingRequestModel.getTotalPrice()));
    }

    @Test
    @DisplayName("Unsuccessful update booking without auth token")
    public void UpdateBookingWithoutAuthTokenReturns403() {
        TestData testData = new TestData();
        BookingRequestModel bookingRequestModel = testData.createBookingRequestModel();

        step("Make update data request without auth token and verify it returns status code 403", () ->
                given(baseRequestSpec)
                        .body(bookingRequestModel)
                        .contentType(JSON)
                        .when()
                        .put("booking/7")
                        .then()
                        .assertThat().statusCode(403));
    }
}
