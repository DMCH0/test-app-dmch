package com.testapp.playercontroller.player_delete;

import com.testapp.constants.IEndpoints;
import com.testapp.constants.IRequestParams;
import com.testapp.constants.IRoles;
import com.testapp.dto.playercontroller.Request.PlayerIdRequestDto;
import com.testapp.playercontroller.basetest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.testapp.helper.BaseClass.getRandomNumber;
import static com.testapp.helper.Request.sendDeleteRequest;
import static com.testapp.specifications.playercontroller.Specifications.setRequiredSpecificWithPathParams;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;

public class SP_0006 extends BaseTest implements IEndpoints, IRoles, IRequestParams {

    @DataProvider(name = "data")
    public Object[][] createPlayerData() {
        return new Object[][]{
                {ADMIN, getRandomNumber(100000, 9999999)},
                {SUPERVISOR, getRandomNumber(99999, 9999999)},
                {SUPERVISOR, 1}
        };
    }

    @Test(dataProvider = "data", enabled = false)
    //TODO Enable test after issue fix
    @Issue("Code 403 instead 404 for Deletion of non-existing user,  Issue key: SPBUG-0051")
    @Owner("Dmytro")
    @Description("[GET] Positive (CODE 400): Deletion of non-existing player on route /player/delete/{editor}")
    public void testDeletionOfNonExistingPlayers(String editor, long playerId) {

        PlayerIdRequestDto playerIdRequestDto = new PlayerIdRequestDto(playerId);

        sendDeleteRequest(setRequiredSpecificWithPathParams(
                        EDITOR, editor),
                playerIdRequestDto,
                PLAYER_DELETE,
                SC_NOT_FOUND
        );
    }
}
