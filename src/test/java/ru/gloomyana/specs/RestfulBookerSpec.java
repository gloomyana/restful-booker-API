package ru.gloomyana.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.notNullValue;
import static ru.gloomyana.helpers.CustomAllureListener.withCustomTemplates;

public class RestfulBookerSpec {
    public static RequestSpecification baseRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .baseUri("https://restful-booker.herokuapp.com");

    public static ResponseSpecification authResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectBody("token", notNullValue())
            .build();

    public static ResponseSpecification bookingCreateResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectBody("bookingid", notNullValue())
            .expectBody("booking", notNullValue())
            .build();

    public static ResponseSpecification bookingResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectBody("firstname", notNullValue())
            .expectBody("lastname", notNullValue())
            .expectBody("totalprice", notNullValue())
            .expectBody("depositpaid", notNullValue())
            .expectBody("bookingdates", notNullValue())
            .build();
}
