package com.couchcoding.oauth.oauth.entity;

import lombok.Data;

@Data
public class UserInfo {
    private String uid;
    private String email;
    private String nickname;

    public UserInfo(String uid, String email, String nickname) {
        this.uid = uid;
        this.email = email;
        this.nickname = nickname;
    }
}
