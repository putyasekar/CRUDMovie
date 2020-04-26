package com.diki.idn.crudmovie.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResponseReadMovie {

    @SerializedName("user")
    private List<UserItem> user;

    public void setUser(List<UserItem> user) {
        this.user = user;
    }

    public List<UserItem> getUser() {
        return user;
    }

    @Override
    public String toString() {
        return
                "ResponseReadMovie{" +
                        "user = '" + user + '\'' +
                        "}";
    }
}