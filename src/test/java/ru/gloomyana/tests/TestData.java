package ru.gloomyana.tests;

import com.github.javafaker.Faker;
import ru.gloomyana.models.BookingDatesModel;
import ru.gloomyana.models.BookingRequestModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestData {
    final Faker faker = new Faker();
    String checkin, checkout;

    public void getDates() {
        LocalDateTime checkInDate = LocalDateTime.now();
        LocalDateTime checkOutDate = checkInDate.plusDays(3);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        checkin = checkInDate.format(dateTimeFormatter);
        checkout = checkOutDate.format(dateTimeFormatter);
    }

    String firstName, lastName, additionalNeeds;
    String[] needs = new String[]{"Breakfast", "Pets are allowed", "Twin beds", "Free parking", "None"};
    int totalPrice;
    boolean depositPaid;

    public String getRandomItemFromArray(String[] items) {
        return faker.options().option(items);
    }

    public void GenerateData() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        additionalNeeds = getRandomItemFromArray(needs);
        totalPrice = faker.number().numberBetween(100, 1000);
        depositPaid = faker.bool().bool();
    }

    BookingRequestModel createBookingRequestModel() {
        GenerateData();
        getDates();

        return BookingRequestModel.builder()
                .firstname(firstName)
                .lastname(lastName)
                .totalPrice(totalPrice)
                .depositPaid(depositPaid)
                .bookingDatesModel(
                        BookingDatesModel.builder()
                                .checkin(checkin)
                                .checkout(checkout)
                                .build())
                .additionalNeeds(additionalNeeds).
                build();
    }
}
