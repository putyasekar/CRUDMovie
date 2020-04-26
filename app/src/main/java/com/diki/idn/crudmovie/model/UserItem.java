package com.diki.idn.crudmovie.model;

import com.google.gson.annotations.SerializedName;

public class UserItem {

    @SerializedName("description")
    private String description;

    @SerializedName("title")
    private String title;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return
                "UserItem{" +
                        "description = '" + description + '\'' +
                        ",title = '" + title + '\'' +
                        "}";
    }
}