package ru.gloomyana.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gloomyana.models.BookingRequestModel;
import ru.gloomyana.models.BookingResponseModel;
import ru.gloomyana.models.CreateBookingResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.gloomyana.helpers.ApiHelpers.createBooking;
import static ru.gloomyana.helpers.ApiHelpers.updateBooking;
import static ru.gloomyana.specs.RestfulBookerSpec.baseRequestSpec;

@Epic("API tests for restful-booker")
@Feature("Create and update booking")
@Tag("api")
@Owner("gloomyana")
public class CreateAndUpdateBookingTests extends TestBase {

    @Test
    @DisplayName("Successful create a new booking")
    public void successfulCreateNewBooking() {
        BookingRequestModel bookingRequestModel = testData.createBookingRequestModel();

        CreateBookingResponseModel response = step("Make create booking request", () ->
                createBooking(bookingRequestModel, token));
        step("Verify successful create new booking id", () ->
                assertThat(response.getBookingId()).isNotNull());
        step("Verify successful create new booking with request data", () ->
                assertThat(response.getBookingRequestModel().equals(bookingRequestModel)));
    }

    @Test
    @DisplayName("Successful update booking data by id")
    public void successfulUpdateBooking() {
        BookingRequestModel bookingRequestModel = testData.createBookingRequestModel();
        int id = createBooking(bookingRequestModel, token).getBookingId();
        BookingRequestModel newBookingRequestModel = testData.createBookingRequestModel();

        BookingResponseModel response = step("Make update all booking data request", () ->
                updateBooking(newBookingRequestModel, token, id));
        step("Verify successful update firstname", () ->
                assertThat(response.getFirstname()).isEqualTo(newBookingRequestModel.getFirstname()));
        step("Verify successful update lastname", () ->
                assertThat(response.getLastname()).isEqualTo(newBookingRequestModel.getLastname()));
        step("Verify successful update total price", () ->
                assertThat(response.getTotalPrice()).isEqualTo(newBookingRequestModel.getTotalPrice()));
    }

    @Test
    @DisplayName("Unsuccessful update booking without auth token")
    public void UpdateBookingWithoutAuthTokenReturns403() {
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
