package com.testapp.dto.playercontroller.Response;

import java.util.List;

public class PlayerGetAllResponseDto {

    private List<PlayerItem> players;

    public List<PlayerItem> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerItem> players) {
        this.players = players;
    }

    public static class PlayerItem {
        private long id;
        private String screenName;
        private String gender;
        private int age;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getScreenName() {
            return screenName;
        }

        public void setScreenName(String screenName) {
            this.screenName = screenName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
