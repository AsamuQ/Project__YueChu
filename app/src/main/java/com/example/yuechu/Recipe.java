package com.example.yuechu;

import java.io.Serializable;

public class Recipe implements Serializable {
    private String name;
    private String description;
    private int portrait;

    public Recipe(int portrait,String name, String description ) {
        this.portrait = portrait;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPortrait() {
        return portrait;
    }

    public void setPortrait(int portrait) {
        this.portrait = portrait;
    }
}
