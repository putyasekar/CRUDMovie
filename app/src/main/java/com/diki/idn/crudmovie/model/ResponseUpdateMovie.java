package com.diki.idn.crudmovie.model;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdateMovie {

    @SerializedName("hasil")
    private String hasil;

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getHasil() {
        return hasil;
    }

    @Override
    public String toString() {
        return
                "ResponseUpdateMovie{" +
                        "hasil = '" + hasil + '\'' +
                        "}";
    }
}