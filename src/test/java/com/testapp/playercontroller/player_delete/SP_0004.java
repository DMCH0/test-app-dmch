package com.testapp.playercontroller.player_delete;

import com.testapp.constants.IEndpoints;
import com.testapp.constants.IRequestParams;
import com.testapp.constants.IRoles;
import com.testapp.dto.playercontroller.Response.PlayerCreateResponseDto;
import com.testapp.playercontroller.basetest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.testapp.helper.BaseClass.getRandomNumber;
import static com.testapp.helper.BaseClass.getRandomString;
import static com.testapp.helper.Request.sendGetRequest;
import static com.testapp.specifications.playercontroller.Specifications.setRequiredSpecificWithParams;
import static com.testapp.utils.methodsfortest.PlayerController.deletePlayer;
import static org.apache.http.HttpStatus.SC_OK;

public class SP_0004 extends BaseTest implements IEndpoints, IRoles, IRequestParams {

    @DataProvider(name = "data")
    public Object[][] createPlayerData() {
        return new Object[][]{
                /** Case - editor for deletion: supervisor and role user */
                {SUPERVISOR, "female", getRandomString("123aBcD!_0"), USER, getRandomNumber(16, 60)},

                /** Case - editor for deletion: supervisor and role admin */
                {SUPERVISOR, "female", getRandomString("123aBcD!_0"), USER, getRandomNumber(16, 60)}
        };
    }

    @Test(dataProvider = "data")
    @Owner("Dmytro")
    @Description("[GET] Positive (CODE 200): Deletion of existing user on route /player/delete/{editor}")
    public void testDeletionOfExistingPlayer(String editor, String gender, String randomString, String role, int age) {

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
        long id = player.getId();

        deletePlayer(editor, id);
    }
}
