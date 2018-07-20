package com.example.yuechu.page_my;

import java.io.Serializable;

public class Material implements Serializable {
    private String m_name;
    private String m_num;

    public Material(String m_name, String m_num) {
        this.m_name = m_name;
        this.m_num = m_num;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_num() {
        return m_num;
    }

    public void setM_num(String m_num) {
        this.m_num = m_num;
    }
}
