package ru.gloomyana.tests;

import com.github.javafaker.Faker;
import ru.gloomyana.models.BookingDatesModel;
import ru.gloomyana.models.BookingRequestModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestData {
    final Faker faker = new Faker();

    LocalDateTime checkInDate = LocalDateTime.now();
    LocalDateTime checkOutDate = checkInDate.plusDays(3);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String checkin = checkInDate.format(dateTimeFormatter);
    String checkout = checkOutDate.format(dateTimeFormatter);

    String[] needs = new String[]{"Breakfast", "Pets are allowed", "Twin beds", "Free parking", "None"};

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            additionalNeeds = faker.options().option(needs);
    int totalPrice = faker.number().numberBetween(100, 1000);

    boolean depositPaid = faker.bool().bool();

    BookingRequestModel createBookingRequestModel() {
        BookingRequestModel bookingRequestModel = new BookingRequestModel();
        BookingDatesModel bookingDatesModel = new BookingDatesModel();

        bookingRequestModel.setFirstname(firstName);
        bookingRequestModel.setLastname(lastName);
        bookingRequestModel.setTotalPrice(totalPrice);
        bookingRequestModel.setDepositPaid(depositPaid);
        bookingDatesModel.setCheckin(checkin);
        bookingDatesModel.setCheckout(checkout);
        bookingRequestModel.setBookingDatesModel(bookingDatesModel);
        bookingRequestModel.setAdditionalNeeds(additionalNeeds);

        return bookingRequestModel;
    }
}
