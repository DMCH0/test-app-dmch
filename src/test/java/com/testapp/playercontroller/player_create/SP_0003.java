package com.testapp.playercontroller.player_create;

import com.testapp.constants.IEndpoints;
import com.testapp.constants.IRequestParams;
import com.testapp.constants.IRoles;
import com.testapp.playercontroller.basetest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.testapp.helper.BaseClass.getRandomNumber;
import static com.testapp.helper.BaseClass.getRandomString;
import static com.testapp.helper.Request.sendGetRequest;
import static com.testapp.specifications.playercontroller.Specifications.setRequiredSpecificWithParams;
import static org.apache.http.HttpStatus.*;

public class SP_0003 extends BaseTest implements IEndpoints, IRoles, IRequestParams {

    @DataProvider(name = "data")
    public Object[][] createPlayerData() {
        return new Object[][]{
                /** Case - editor null */
                {"null", "male", getRandomString("123aBcD!_0"), USER, getRandomNumber(17, 60), SC_FORBIDDEN},

                /** Case - age more than 60 years */
                {SUPERVISOR, "male", getRandomString("123aBcD!_0"), ADMIN, getRandomNumber(61, 110), SC_BAD_REQUEST}
        };
    }

    @Test(dataProvider = "data")
    @Owner("Dmytro")
    @Description("[GET] Negative (CODE 400/403): Failed createPlayer request on route /player/create/{editor}")
    public void testFailCreatePlayer(String editor, String gender, String randomString, String role, int age, int statusCode) {

        sendGetRequest(
                setRequiredSpecificWithParams(
                        EDITOR, editor,
                        AGE, age,
                        GENDER, gender,
                        LOGIN, randomString,
                        PASSWORD, randomString,
                        ROLE, role,
                        SCREEN_NAME, randomString),
                PLAYER_CREATE,
                statusCode
        );
    }
}
