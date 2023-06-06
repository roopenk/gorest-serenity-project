package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    @Title("Test to verify the total number of records displayed on a page")
    @Test
    public void test001() {
        response.body(("size()"), equalTo(10));
    }

    @Title("Test to verify the title of from ID")
    @Test
    public void test002() {
        response.body("findAll{it.id == 38981}.title", hasItem("Reprehenderit volva dedico ustulo suggero minima molestias ut cursim."));
    }

    @Title("Test to verify that a particular ID exists or not")
    @Test
    public void test003() {
        response.body("findAll{it}.id", hasItem(38981));
    }

    @Title("Test to verify That multiple IDs exist")
    @Test
    public void test004() {
        response.body("findAll{it}.id", hasItems(38981, 38980, 38979));
    }

    @Title("Test to verify the body of an ID")
    @Test
    public void test005() {
        response.body("findAll{it.id == 38981}.body", hasItem("Atavus cruentus thymum. Vix et custodia. Praesentium qui suasoria. Est tersus ducimus. Tepidus perspiciatis adipiscor. Debilito desidero virtus. Vis teneo demonstro. Patria adflicto cursus. Victoria molestias demens. Damnatio tempus tunc. Vulnus creator quibusdam. Derelinquo arceo deprecator. Comitatus clamo tonsor. Occaecati virga thorax. Aegrotatio succurro somnus. Distinctio culpa tabella. Omnis patrocinor delectus. Non voco accipio. Virtus adficio vitiosus."));
    }
}
