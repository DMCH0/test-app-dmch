package com.testapp.helper;

import com.testapp.dto.playercontroller.Response.PlayerGetAllResponseDto;
import org.testng.Assert;

import java.util.List;

public class PlayerValidator {

    public static void validatePlayers(List<PlayerGetAllResponseDto.PlayerItem> players) {
        Assert.assertNotNull(players, "players list must not be null");
        Assert.assertFalse(players.isEmpty(), "players list must not be empty");

        players.forEach(p -> {
            Assert.assertTrue(p.getId() > 0, "id must be positive");
            Assert.assertNotNull(p.getScreenName(), "screenName must not be null");
            Assert.assertFalse(p.getScreenName().isBlank(), "screenName must not be blank");
            Assert.assertTrue(p.getAge() >= 16 && p.getAge() <= 60, "age must be between 16 and 60 inclusive");
            Assert.assertTrue(
                    p.getGender().equalsIgnoreCase("male") || p.getGender().equalsIgnoreCase("female"),
                    "gender must be male or female"
            );
        });

        Assert.assertEquals(
                players.stream()
                        .map(PlayerGetAllResponseDto.PlayerItem::getId)
                        .distinct()
                        .count(),
                players.size(),
                "duplicate ids found"
        );

        Assert.assertEquals(
                players.stream()
                        .map(PlayerGetAllResponseDto.PlayerItem::getScreenName)
                        .distinct()
                        .count(),
                players.size(),
                "duplicate screenNames found"
        );

        Assert.assertTrue(
                players.stream().anyMatch(p -> "testSupervisor".equalsIgnoreCase(p.getScreenName())),
                "supervisor must exist in the system"
        );
    }
}
