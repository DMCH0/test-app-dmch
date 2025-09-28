package com.testapp.helper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Request {

    public static Response sendPostRequest(RequestSpecification reqSpec, Object object,
                                           String endpoint, int expectedStatusCode) {
        return given()
                .spec(reqSpec)
                .when()
                .body(object)
                .log().all()
                .post(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
    }

    public static Response sendGetRequest(RequestSpecification reqSpec,
                                          String endpoint, int expectedStatusCode) {
        return given()
                .spec(reqSpec)
                .when()
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
    }

    public static Response sendPatchRequest(RequestSpecification reqSpec, Object object,
                                           String endpoint, int expectedStatusCode) {
        return given()
                .spec(reqSpec)
                .when()
                .body(object)
                .log().all()
                .patch(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
    }

    public static Response sendDeleteRequest(RequestSpecification reqSpec, Object object,
                                             String endpoint, int expectedStatusCode) {
        return given()
                .spec(reqSpec)
                .when()
                .body(object)
                .delete(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
    }
}
