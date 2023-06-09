package ru.gloomyana.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gloomyana.models.BookingRequestModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static ru.gloomyana.helpers.ApiHelpers.createBooking;
import static ru.gloomyana.specs.RestfulBookerSpec.baseRequestSpec;

@Epic("API tests for restful-booker")
@Feature("Delete booking")
@Tag("api")
@Owner("gloomyana")
public class DeleteBookingTests extends TestBase {

    @Test
    @DisplayName("Delete request returns status 201")
    public void deleteBookingReturns201() {
        BookingRequestModel bookingRequestModel = testData.createBookingRequestModel();
        int id = createBooking(bookingRequestModel, token).getBookingId();

        step("Make booking delete request and verify it returns status code 201", () ->
                given(baseRequestSpec)
                        .header("Cookie", "token=" + token)
                        .when()
                        .delete("/booking/" + id)
                        .then()
                        .assertThat().statusCode(201));
    }
}
