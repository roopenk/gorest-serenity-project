package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import com.gorest.userinfo.PostSteps;
import com.gorest.userinfo.UserSteps;
import com.gorest.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {

    static int userId;
    static String name = "user111" + TestUtils.getRandomValue();
    static String email = "user111" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";

    @Steps
    UserSteps userSteps;
    @Steps
    PostSteps postSteps;

    @Title("Test To Create A New User Record")
    @Test
    public void test001() {
        userId = postSteps.createUser(name, email, gender, status).statusCode(201).extract().path("id");
    }

    @Title("Test To Update Existing User Record")
    @Test
    public void test002() {
        postSteps.updateUserByUsingPut(userId, name, email, gender, status).statusCode(200).log().all();
    }

    @Title("Test To Delete The User Record")
    @Test
    public void test003() {
        userSteps.deleteUser(userId).statusCode(204);
        userSteps.verifyDeleteOperation(userId).statusCode(404);
    }
}
