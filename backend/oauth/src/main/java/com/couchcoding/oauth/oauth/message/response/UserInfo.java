package com.couchcoding.oauth.oauth.message.response;

import com.couchcoding.oauth.oauth.domain.user.entity.CustomUser;

import lombok.Data;

@Data
public class UserInfo {
    private String uid;
    private String email;
    private String nickname;

    public UserInfo(CustomUser customUser) {
        this.uid = customUser.getUsername();
        this.email = customUser.getEmail();
        this.nickname = customUser.getNickname();
    }
}
