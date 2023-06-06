package com.gorest.userinfo;

import com.gorest.constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UserSteps {
    @Step("Deleting User With User ID : {0}")
    public ValidatableResponse deleteUser(int userId) {
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer 50a69fea24ca17aa59bbef3bbbf298012fd4bd89a81731d117e1a05114dc9cca")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .pathParam("userId", userId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
    @Step("Verifying Whether The User Record Has Been Deleted By User ID : {0}")
    public ValidatableResponse verifyDeleteOperation(int userId) {
        return SerenityRest.given()
                .header("Authorization", "Bearer 50a69fea24ca17aa59bbef3bbbf298012fd4bd89a81731d117e1a05114dc9cca")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .pathParam("userId", userId)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then();
    }
}
