package com.testapp.utils.methodsfortest;

import com.testapp.constants.IEndpoints;
import com.testapp.constants.IRequestParams;
import com.testapp.constants.IRoles;
import com.testapp.dto.playercontroller.Request.PlayerIdRequestDto;

import static com.testapp.helper.Request.sendDeleteRequest;
import static com.testapp.specifications.playercontroller.Specifications.setRequiredSpecificWithPathParams;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;

public class PlayerController  implements IEndpoints, IRoles, IRequestParams {

    public static void deletePlayer(String editor, long playerId){

        PlayerIdRequestDto playerIdRequestDto = new PlayerIdRequestDto(playerId);
        sendDeleteRequest(
                setRequiredSpecificWithPathParams(
                        EDITOR, editor),
                playerIdRequestDto,
                PLAYER_DELETE,
                SC_NO_CONTENT
        );
    }
}
