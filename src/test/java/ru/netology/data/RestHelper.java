package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

import static io.restassured.RestAssured.given;

@UtilityClass
public class RestHelper {

    public String pathBuy = "/api/v1/pay";
    public String pathCredit = "/api/v1/credit";

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static String sentFormBuy(DataHelper.FormFields cardData) {
        return given()
                .spec(requestSpec)
                .body(cardData)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200)
                .extract().response().asString();
    }

    public static String sentFormCredit(DataHelper.FormFields cardData) {
        return given()
                .spec(requestSpec)
                .body(cardData)
                .when()
                .post(pathCredit)
                .then()
                .statusCode(200)
                .extract().response().asString();
    }
}
