package com.techmesystem.intera.home.model;

import java.io.Serializable;

public class HomeDataModel implements Serializable {
    private int picture;
    private String name;

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
