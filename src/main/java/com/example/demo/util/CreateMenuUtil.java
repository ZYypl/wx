package com.example.demo.util;

import com.example.demo.entity.menu.*;
import net.sf.json.JSONObject;

/**
 * com.example.demo.util
 *
 * @author ypl
 * @create 2020-04-09 15:53
 */

//自定义创建菜单
public class CreateMenuUtil {

    //删除菜单
    //GET https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN


    private static String MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public static  void main(String[] args) {

        ButtonMenu buttonMenu = new ButtonMenu();
        buttonMenu.getButton().add(new ClickButton("一级点击","1"));
        buttonMenu.getButton().add(new ViewButton("一级跳转","http://www.baidu.com"));

        //为第三个一级菜单加入二级菜单
        SubButtonMenu subButtonMenu = new SubButtonMenu("一级子菜单");
        subButtonMenu.getSub_button().add(new ClickButton("二级点击","31"));
        subButtonMenu.getSub_button().add(new PhotoOrAlbumButton("二级传图","32"));
        subButtonMenu.getSub_button().add(new ViewButton("二级跳转","http://news.163.com"));

        //加入第三个一级菜单
        buttonMenu.getButton().add(subButtonMenu);

        JSONObject jsonObjects = JSONObject.fromObject(buttonMenu);
        System.out.println(jsonObjects.toString());

        //token
        String menuUrl = MENU_URL.replace("ACCESS_TOKEN",GetTokenUtil.getToken());

        //发送请求
        String s1 = GetTokenUtil.postFromUrl(menuUrl, jsonObjects.toString());

        System.out.println(s1);

        /**
         * {"button":[{"name":"一级点击","type":"click","key":"1"},{"name":"一级跳转","type":"view","url":"http://www.baidu.com"},{"name":"一级子菜单","sub_button":[{"name":"二级点击","type":"click","key":"31"},{"name":"二级传图","sub_button":[],"type":"pic_photo_or_album","key":"32"},{"name":"二级跳转","type":"view","url":"http://news.163.com"}]}]}
         * {"errcode":0,"errmsg":"ok"}
         */
    }
}
