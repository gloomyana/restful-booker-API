package ru.gloomyana.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingRequestModel {
    String firstname, lastname;

    @JsonProperty("totalprice")
    int totalPrice;

    @JsonProperty("depositpaid")
    boolean depositPaid;

    @JsonProperty("bookingdates")
    BookingDatesModel bookingDatesModel;

    @JsonProperty("additionalneeds")
    String additionalNeeds;
}
