package ru.gloomyana.tests;

import com.github.javafaker.Faker;
import ru.gloomyana.models.BookingDatesModel;
import ru.gloomyana.models.BookingRequestModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestData {
    final Faker faker = new Faker();
    String checkin, checkout;

    public LocalDate generateRandomDate() {
        int year = faker.number().numberBetween(2020, 2050);
        int month = faker.number().numberBetween(1, 12);
        int day = faker.number().numberBetween(1, 28);
        return LocalDate.of(year, month, day);
    }

    public void getDates() {
        LocalDate checkInDate = generateRandomDate();
        LocalDate checkOutDate = checkInDate.plusDays(faker.number().numberBetween(1, 30));
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

    public void generateData() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        additionalNeeds = getRandomItemFromArray(needs);
        totalPrice = faker.number().numberBetween(100, 1000);
        depositPaid = faker.bool().bool();
    }

    BookingRequestModel createBookingRequestModel() {
        generateData();
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
