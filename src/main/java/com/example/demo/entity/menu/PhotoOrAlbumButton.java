package com.example.demo.entity.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * com.example.demo.entity.menu
 *
 * @author ypl
 * @create 2020-04-09 15:33
 */

//传图按钮
public class PhotoOrAlbumButton  extends AbstractButtonMenu{

    private String type="pic_photo_or_album";

    private String key;

    private List<AbstractButtonMenu> sub_button = new ArrayList<>();


    public PhotoOrAlbumButton() {
    }

    public PhotoOrAlbumButton(String name, String key) {
        super(name);
        this.key = key;
    }

    public PhotoOrAlbumButton(String name, String key, List<AbstractButtonMenu> sub_button) {
        super(name);
        this.key = key;
        this.sub_button = sub_button;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<AbstractButtonMenu> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<AbstractButtonMenu> sub_button) {
        this.sub_button = sub_button;
    }
}
