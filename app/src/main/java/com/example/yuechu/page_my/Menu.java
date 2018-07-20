package com.example.yuechu.page_my;

import java.io.Serializable;
import java.util.ArrayList;

public class Menu implements Serializable {
    private ArrayList<Material> main_list;
    private ArrayList<Material> fu_list;
    private ArrayList<String> step_list;
    private ArrayList<String> steppic_list;
    private ArrayList<String> other;

    public Menu( ArrayList<String> step_list, ArrayList<String> steppic_list) {
        this.step_list = step_list;
        this.steppic_list = steppic_list;
    }

    public Menu(ArrayList<Material> main_list, ArrayList<Material> fu_list, ArrayList<String> step_list, ArrayList<String> steppic_list, ArrayList<String> other) {
        this.main_list = main_list;
        this.fu_list = fu_list;
        this.step_list = step_list;
        this.steppic_list = steppic_list;
        this.other = other;
    }

    public ArrayList<Material> getMain_list() {
        return main_list;
    }

    public void setMain_list(ArrayList<Material> main_list) {
        this.main_list = main_list;
    }

    public ArrayList<Material> getFu_list() {
        return fu_list;
    }

    public void setFu_list(ArrayList<Material> fu_list) {
        this.fu_list = fu_list;
    }

    public ArrayList<String> getStep_list() {
        return step_list;
    }

    public void setStep_list(ArrayList<String> step_list) {
        this.step_list = step_list;
    }

    public ArrayList<String> getSteppic_list() {
        return steppic_list;
    }

    public void setSteppic_list(ArrayList<String> steppic_list) {
        this.steppic_list = steppic_list;
    }

    public ArrayList<String> getOther() {
        return other;
    }

    public void setOther(ArrayList<String> other) {
        this.other = other;
    }
}
