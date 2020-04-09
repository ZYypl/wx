package com.example.demo.entity.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * com.example.demo.entity.menu
 *
 * @author ypl
 * @create 2020-04-09 15:12
 */

//菜单按钮
    //一级菜单

public class ButtonMenu {

    List<AbstractButtonMenu> button = new ArrayList<>();

    public ButtonMenu(List<AbstractButtonMenu> button) {
        this.button = button;
    }

    public ButtonMenu() {
    }

    public List<AbstractButtonMenu> getButton() {
        return button;
    }

    public void setButton(List<AbstractButtonMenu> button) {
        this.button = button;
    }
}


