package com.example.demo.entity.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * com.example.demo.entity.menu
 *
 * @author ypl
 * @create 2020-04-09 15:24
 */

//二级菜单

public class SubButtonMenu extends AbstractButtonMenu {



    private List<AbstractButtonMenu> sub_button = new ArrayList<>();

    public SubButtonMenu() {
    }

    public SubButtonMenu(String name) {
        super(name);
    }

    public SubButtonMenu(String name, List<AbstractButtonMenu> sub_button) {
        super(name);
        this.sub_button = sub_button;
    }

    public List<AbstractButtonMenu> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<AbstractButtonMenu> sub_button) {
        this.sub_button = sub_button;
    }
}
