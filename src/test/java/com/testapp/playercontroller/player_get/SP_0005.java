package com.testapp.playercontroller.player_get;

import com.testapp.constants.IEndpoints;
import com.testapp.constants.IRoles;
import com.testapp.dto.playercontroller.Request.PlayerIdRequestDto;
import com.testapp.dto.playercontroller.Response.PlayerResponseDto;
import com.testapp.playercontroller.basetest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.testapp.helper.Request.sendPostRequest;
import static com.testapp.specifications.playercontroller.Specifications.SpecificationPlayerController;
import static org.apache.http.HttpStatus.SC_OK;

public class SP_0005 extends BaseTest implements IEndpoints, IRoles {

    @Test()
    @Owner("Dmytro")
    @Description("[POST] Positive (CODE 200): Get existing player by id on route /player/get")
    public void testGetExistingPlayers() {

        PlayerIdRequestDto playerIdRequestDto = new PlayerIdRequestDto(1);

        Response response = sendPostRequest(
                SpecificationPlayerController(),
                playerIdRequestDto,
                PLAYER_GET,
                SC_OK
        );

        PlayerResponseDto player = response.as(PlayerResponseDto.class);

        Assert.assertEquals(player.getId(), 1);
        Assert.assertEquals(player.getLogin(), SUPERVISOR);
        Assert.assertEquals(player.getPassword(), "testSupervisor");
        Assert.assertEquals(player.getScreenName(), "testSupervisor");
        Assert.assertEquals(player.getGender(), "male");
        Assert.assertEquals(player.getAge(), 28);
        Assert.assertEquals(player.getRole(), "supervisor");
    }
}
