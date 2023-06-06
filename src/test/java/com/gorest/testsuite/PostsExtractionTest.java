package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    @Title("Test to extract the title of a particular record")
    @Test
    public void test001() {
        System.out.println("The Title Of ID 39297 : " + response.extract().path("findAll{it.id == 39297 }.title"));
    }

    @Title("Test to extract total number of records")
    @Test
    public void test002() {
        System.out.println("The Total Number Of Records : " + response.extract().path("size()"));
    }

    @Title("Test to extract the response body of a particular record")
    @Test
    public void test003() {
        System.out.println("The Response Body Of Record Number 6 : " + response.extract().path("findAll{it.id}.body[5]"));
    }

    @Title("Test to extract all user_id")
    @Test
    public void test004() {
        System.out.println("The User IDs Of All Records : " + response.extract().path("findAll{it.id}.user_id"));
    }

    @Title("Test to extract all titles from the response body")
    @Test
    public void test005() {
        System.out.println("All Titles From The Response Body : " + response.extract().path("findAll{it.id}.title"));
    }

    @Title("Test to extract the response body of by id")
    @Test
    public void test006() {
        System.out.println("The Response Body Of Record Having ID As 39296 : " + response.extract().path("findAll{it.id == 39296}.body"));
    }
}
