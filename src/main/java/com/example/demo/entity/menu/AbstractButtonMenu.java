package com.example.demo.entity.menu;

/**
 * com.example.demo.entity.menu
 *
 * @author ypl
 * @create 2020-04-09 15:14
 */

public abstract class AbstractButtonMenu {


    private String name;

    public AbstractButtonMenu() {
    }

    public AbstractButtonMenu(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
