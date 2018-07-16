package com.example.yuechu;

import java.io.Serializable;

public class Recipe implements Serializable {
    private String portrait;
    private String name;
    private String description;

    public Recipe(String  portrait,String name, String description ) {
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

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void add(Recipe recipe){
        this.portrait=recipe.getPortrait();
        this.name=recipe.getName();
        this.description=recipe.getDescription();
    }
}
