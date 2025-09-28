package com.testapp.playercontroller.player_update;

import com.testapp.constants.IEndpoints;
import com.testapp.constants.IRequestParams;
import com.testapp.constants.IRoles;
import com.testapp.dto.playercontroller.Request.PlayerUpdateRequestDto;
import com.testapp.dto.playercontroller.Response.PlayerUpdateResponseDTO;
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
import static com.testapp.helper.Request.sendPatchRequest;
import static com.testapp.specifications.playercontroller.Specifications.setRequiredSpecificWithPathParams;
import static org.apache.http.HttpStatus.SC_OK;

public class SP_0008 extends BaseTest implements IEndpoints, IRequestParams, IRoles {

    @DataProvider(name = "data")
    public Object[][] createPlayerData() {
        return new Object[][]{
                {SUPERVISOR, 1100469915, "female", "upd_" + getRandomString("1230AB"), "user", getRandomNumber(16, 60)},
                {ADMIN, 1100469915, "male", "upd_" + getRandomString("1230AB"), "admin",  getRandomNumber(16, 60)}
        };
    }

    @Test(dataProvider = "data")
    //TODO Create an issue
    @Issue("Role admin instead user + age didn't changed/ Role adin can't update player")
    @Owner("Dmytro")
    @Description("[GET] Positive (CODE 200): Update existing player by id on route /player/update/{editor}/{id}")
    public void testGetAllPlayers(String editor, long playerId, String gender, String login, String role, int age) {

        PlayerUpdateRequestDto updateRequest = new PlayerUpdateRequestDto();
        updateRequest.setAge(age);
        updateRequest.setGender(gender);
        updateRequest.setLogin(login);
        updateRequest.setPassword("NewPass12345");
        updateRequest.setRole(role);
        updateRequest.setScreenName("NewScreen123");

        Response response = sendPatchRequest(
                setRequiredSpecificWithPathParams(
                        EDITOR, editor,
                        ID, (playerId)),
                updateRequest,
                PLAYER_UPDATE,
                SC_OK
        );

        PlayerUpdateResponseDTO player = response.as(PlayerUpdateResponseDTO .class);

        Assert.assertEquals(player.getId(), playerId, "Id in response does not match requested id");
        Assert.assertEquals(player.getAge(), age, "age must be updated");
        Assert.assertEquals(player.getGender(), gender, "gender must be updated");
        Assert.assertEquals(player.getLogin(), login, "login must be updated");
        Assert.assertEquals(player.getRole(), role, "role must be updated");
        Assert.assertEquals(player.getScreenName(), "updatedScreen123", "screenName must be updated");
    }
}
