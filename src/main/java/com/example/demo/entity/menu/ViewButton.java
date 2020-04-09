package com.example.demo.entity.menu;

/**
 * com.example.demo.entity.menu
 *
 * @author ypl
 * @create 2020-04-09 15:21
 */


public class ViewButton extends AbstractButtonMenu {


    private String type="view";

    private String url;

    public ViewButton() {
    }

    public ViewButton(String name, String url) {
        super(name);
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
