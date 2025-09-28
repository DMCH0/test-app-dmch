package com.testapp.playercontroller.player_create;

import com.testapp.constants.IEndpoints;
import com.testapp.constants.IRequestParams;
import com.testapp.constants.IRoles;
import com.testapp.dto.playercontroller.Response.PlayerCreateResponseDto;
import com.testapp.playercontroller.basetest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.testapp.helper.BaseClass.getRandomNumber;
import static com.testapp.helper.BaseClass.getRandomString;
import static com.testapp.helper.Request.sendGetRequest;
import static com.testapp.specifications.playercontroller.Specifications.setRequiredSpecificWithParams;
import static org.apache.http.HttpStatus.SC_OK;

public class SP_0002 extends BaseTest implements IEndpoints, IRoles, IRequestParams {

    @DataProvider(name = "data")
    public Object[][] createPlayerData() {
        return new Object[][]{
                /** Case - min age and role supervisor */
                {SUPERVISOR, "female", getRandomString("123aBcD!_0"), USER, 16},

                /** Case - max age and role supervisor */
                {SUPERVISOR, "male", getRandomString("123aBcD!_0"), USER, 60},

                /** Case - random age and role admin */
                {ADMIN, "male", getRandomString("123aBcD!_0"), ADMIN, getRandomNumber(17, 59)}
        };
    }

    @Test(dataProvider = "data", enabled = false)
    //TODO Enable test after issue fix
    @Issue("Age 16 trigger Code 400/ Code 403 for editor admin/ Response with null param for fields: password,gender... " +
            "Issue key: SPBUG-0052/0053/0054")
    @Owner("Dmytro")
    @Description("[GET] Positive (CODE 200): createPlayer on route /player/create/{editor}")
    public void testCreateNewPlayer(String editor, String gender, String randomString, String role, int age) {

        Response response = sendGetRequest(
                setRequiredSpecificWithParams(
                        EDITOR, editor,
                        AGE, age,
                        GENDER, gender,
                        LOGIN, randomString,
                        PASSWORD, randomString,
                        ROLE, role,
                        SCREEN_NAME, randomString),
                PLAYER_CREATE,
                SC_OK
        );
        PlayerCreateResponseDto player = response.as(PlayerCreateResponseDto.class);

        Assert.assertTrue(player.getId() > 0, "id must be generated");
        Assert.assertEquals(player.getLogin(), randomString);
        Assert.assertEquals(player.getScreenName(), randomString);
        Assert.assertEquals(player.getRole(), role);
        Assert.assertEquals(player.getGender().toLowerCase(), gender);
        Assert.assertTrue(
                player.getAge() > 16 && player.getAge() < 60,
                "Age must be strictly >16 and <60, but was: " + player.getAge());
    }
}
