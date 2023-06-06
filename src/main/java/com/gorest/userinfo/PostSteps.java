package com.gorest.userinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.PostsPojo;
import com.gorest.model.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class PostSteps {
    @Step("Creating New User Record With Name : {0}, Email : {1}, Gender : {2} And Status : {3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {
        PostsPojo postsPojo = PostsPojo.getPostsPojo(name, email, gender, status);

        return SerenityRest.given()
                .header("Authorization", "Bearer 50a69fea24ca17aa59bbef3bbbf298012fd4bd89a81731d117e1a05114dc9cca")
                .header("Content-Type", "application/json")
                .body(postsPojo)
                .when()
                .post()
                .then();
    }

    @Step("Updating Created User Record With User ID : {0}, Name : {1}, Email : {2}, Gender : {3} And Status : {4}")
    public ValidatableResponse updateUserByUsingPut(int userId, String name, String email, String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name + "_updated");
        userPojo.setEmail("rockpopone@aol.com");
        userPojo.setGender("female");
        userPojo.setStatus("inactive");
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer 50a69fea24ca17aa59bbef3bbbf298012fd4bd89a81731d117e1a05114dc9cca")
                .header("Content-Type", "application/json")
                .pathParam("userId", userId)
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID) // https://gorest.co.in/public/v2/users/userId
                .then();
    }
}
