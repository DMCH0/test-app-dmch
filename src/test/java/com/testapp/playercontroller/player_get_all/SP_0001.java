package com.testapp.playercontroller.player_get_all;

import com.testapp.constants.IEndpoints;
import com.testapp.dto.playercontroller.Response.PlayerGetAllResponseDto;
import com.testapp.helper.PlayerValidator;
import com.testapp.playercontroller.basetest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.testapp.helper.Request.sendGetRequest;
import static com.testapp.specifications.playercontroller.Specifications.SpecificationPlayerController;
import static org.apache.http.HttpStatus.SC_OK;

public class SP_0001 extends BaseTest implements IEndpoints {

    @Test()
    @Owner("Dmytro")
    @Description("[GET] Positive (CODE 200): getAllPlayers on route /player/get/all")
    public void testGetAllPlayers() {

        Response response = sendGetRequest(
                SpecificationPlayerController(),
                PLAYER_GET_ALL,
                SC_OK
        );

        PlayerGetAllResponseDto dto = response.as(PlayerGetAllResponseDto.class);
        PlayerValidator.validatePlayers(dto.getPlayers());
    }
}

