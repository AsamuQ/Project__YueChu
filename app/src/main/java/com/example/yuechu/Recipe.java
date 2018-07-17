package com.example.yuechu;

import java.io.Serializable;

public class Recipe implements Serializable {
    private String portrait;    //图片uri
    private String name;        //食谱标题
    private String description; //食谱描述
    private String url;         //食谱页面

    public Recipe(String  portrait,String name, String description, String url) {
        this.portrait = portrait;
        this.name = name;
        this.description = description;
        this.url=url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
