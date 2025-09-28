package com.testapp.dto.playercontroller.Request;

public class PlayerIdRequestDto {

    private long playerId;

    public PlayerIdRequestDto(long playerId) {
        this.playerId = playerId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }
}

