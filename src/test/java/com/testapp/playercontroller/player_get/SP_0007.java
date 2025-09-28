package com.testapp.playercontroller.player_get;

import com.testapp.constants.IEndpoints;
import com.testapp.dto.playercontroller.Request.PlayerIdRequestDto;
import com.testapp.playercontroller.basetest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.testapp.helper.Request.sendPostRequest;
import static com.testapp.specifications.playercontroller.Specifications.SpecificationPlayerController;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;

public class SP_0007 extends BaseTest implements IEndpoints {

    @DataProvider(name = "data")
    public Object[][] createPlayerData() {
        return new Object[][]{
                {1400508201},
                {0}
        };
    }

    @Test(dataProvider = "data", enabled = false)
    //TODO Enable test after issue fix
    @Issue("The system responds with code 200 instead 404, Issue key: SPBUG-0050")
    @Owner("Dmytro")
    @Description("[GET] Positive (CODE 404): Get non-existing player by id on route /player/get")
    public void testGetNonExistingPlayers(long playerId) {

        PlayerIdRequestDto playerIdRequestDto = new PlayerIdRequestDto(playerId);

        sendPostRequest(
                SpecificationPlayerController(),
                playerIdRequestDto,
                PLAYER_GET,
                SC_NOT_FOUND
        );
    }
}
