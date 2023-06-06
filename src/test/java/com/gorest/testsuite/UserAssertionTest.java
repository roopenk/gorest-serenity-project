package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasItem;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    @Title("Test to verify that total number of records is 10")
    @Test
    public void test001() {
        response.body(("size()"), equalTo(10));
    }

    @Title("Test to verify that a particular id has the required name")
    @Test
    public void test002() {
        response.body("findAll{it.id == 2250472}.name", hasItem("Laxman Desai"));
    }

    @Title("Test to verify that a particular id has the required name")
    @Test
    public void test003() {
        response.body("findAll{it.id == 2250473}.name", hasItem("Kirti Chaturvedi"));
    }

    @Title("Test to verify that the response body has list of names")
    @Test
    public void test004() {
        response.body("findAll{it.id}.name", hasItems("Trilochana Chattopadhyay", "Laxman Desai", "Kirti Chaturvedi"));
    }

    @Title("Test to verify that the id 2250475 has email as trilochana_chattopadhyay@bahringer.test")
    @Test
    public void test005() {
        response.body("findAll{it.id == 2250475}.email", hasItem("trilochana_chattopadhyay@bahringer.test"));
    }

    @Title("Test to verify the status by name")
    @Test
    public void test006() { // Test to verify that the name Trilochana Chattopadhyay has status as active
        response.body("findAll{it.name == 'Trilochana Chattopadhyay'}.status", hasItem("active"));
    }

    @Title("Test to verify the gender by name")
    @Test
    public void test007() { // Test to verify that the name Trilochana Chattopadhyay has gender as female
        response.body("findAll{it.name == 'Trilochana Chattopadhyay'}.gender", hasItem("female"));
    }
}
