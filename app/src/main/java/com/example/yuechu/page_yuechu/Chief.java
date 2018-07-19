package com.example.yuechu.page_yuechu;

import java.io.Serializable;

public class Chief implements Serializable {
    private String name;
    private int imgsrc;
    private String cheif_dec;

    public Chief() {}

    public Chief(String name, int imgsrc, String cheif_dec) {
        this.name = name;
        this.imgsrc = imgsrc;
        this.cheif_dec = cheif_dec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(int imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getCheif_dec() {
        return cheif_dec;
    }

    public void setCheif_dec(String cheif_dec) {
        this.cheif_dec = cheif_dec;
    }
}
