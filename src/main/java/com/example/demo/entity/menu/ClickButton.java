package com.example.demo.entity.menu;

/**
 * com.example.demo.entity.menu
 *
 * @author ypl
 * @create 2020-04-09 15:18
 */

//点击按钮
public class ClickButton extends AbstractButtonMenu {


    private String key;

    private String type="click";

    public ClickButton() {
    }

    public ClickButton(String name,String key) {
        super(name);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
