package com.testapp.specifications.playercontroller;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.testapp.helper.PropertyReader.PATH_TO_CONFIG;
import static com.testapp.helper.PropertyReader.readProperty;

public class Specifications {

    /**
     * Specification builds and returns a RequestSpecification for PlayerController endpoint group.
     */
    public static RequestSpecification SpecificationPlayerController() {
        return new RequestSpecBuilder()
                .setBaseUri(readProperty(PATH_TO_CONFIG, "base_url"))
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured());
    }

    /**
     * Specification builds and returns a RequestSpecification with Path and Query parameters.
     * The parameters are provided as key-value pairs (even number of arguments).
     */
    public static RequestSpecification setRequiredSpecificWithParams(String pathParam, String pathValue, Object... queryParams) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(readProperty(PATH_TO_CONFIG, "base_url"))
                .setContentType(ContentType.JSON)
                .addPathParam(pathParam, pathValue);

        if (queryParams.length % 2 != 0) {
            throw new IllegalArgumentException("Number of parameters and values must be even");
        }

        for (int i = 0; i < queryParams.length; i += 2) {
            if (!(queryParams[i] instanceof String)) {
                throw new IllegalArgumentException("Parameter name must be a String");
            }
            requestSpecBuilder.addQueryParam((String) queryParams[i], queryParams[i + 1]);
        }

        return requestSpecBuilder.build().log().all().filter(new AllureRestAssured());
    }

    public static RequestSpecification setRequiredSpecificWithPathParams(Object...pathParams) {
        if (pathParams.length % 2 != 0) {
            throw new IllegalArgumentException("Number of parameters and values must be even");
        }

        RequestSpecBuilder specBuilder = new RequestSpecBuilder()
                .setBaseUri(readProperty(PATH_TO_CONFIG, "base_url"))
                .setContentType(ContentType.JSON);

        for (int i = 0; i < pathParams.length; i += 2) {
            Object param = pathParams[i];
            Object value = pathParams[i + 1];

            if (!(param instanceof String)) {
                throw new IllegalArgumentException("Parameter name must be a String");
            }

            specBuilder.addPathParam((String) param, value);
        }

        return specBuilder
                .build()
                .log().all()
                .filter(new AllureRestAssured());
    }
}
