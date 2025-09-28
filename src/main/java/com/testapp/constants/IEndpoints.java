package com.testapp.constants;

public interface IEndpoints {

    String PLAYER_CREATE = "/player/create/{editor}";
    String PLAYER_DELETE = "/player/delete/{editor}";
    String PLAYER_GET = "/player/get";
    String PLAYER_GET_ALL = "/player/get/all";
    String PLAYER_UPDATE = "/player/update/{editor}/{id}";
}
