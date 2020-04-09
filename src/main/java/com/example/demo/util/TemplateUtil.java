package com.example.demo.util;

/**
 * com.example.demo.util
 *
 *
 * 模板消息
 *
 * @author ypl
 * @create 2020-04-09 17:45
 */


public class TemplateUtil {

//1设置所属行业

//    http请求方式:
//    POST
//    https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN

//    {
//            "industry_id1":"1",
//            "industry_id2":"4"
//    }

//2 获取设置的行业信息

//    http请求方式：
//    GET
//    https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN

//    正确调用后的返回示例：
//    {
//        "primary_industry":{"first_class":"运输与仓储","second_class":"快递"},
//        "secondary_industry":{"first_class":"IT科技","second_class":"互联网|电子商务"}
//    }



//  3  获得模板ID

//    从行业模板库选择模板到帐号后台，获得模板ID的过程可在微信公众平台后台完成。为方便第三方开发者，提供通过接口调用的方式来获取模板ID，具体如下：
//    接口调用请求说明
//    http请求方式:
//    POST
//    https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN
//
//    POST数据示例如下：
//    {
//        "template_id_short":"TM00015"
//    }

//   4 发送模板消息
//
//    http请求方式:
//    POST
//    https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
//
//    POST数据示例如下：
//    {
//            "touser":"OPENID",
//            "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
//            "url":"http://weixin.qq.com/download",
//            "miniprogram":{
//                     "appid":"xiaochengxuappid12345",
//                     "pagepath":"index?foo=bar"
//              },
//        "data":{
//        "first": {
//            "value":"恭喜你购买成功！",
//            "color":"#173177"
//        },
//        "keyword1":{
//           "value":"巧克力",
//           "color":"#173177"
//        },
//        "keyword2": {
//           "value":"39.8元",
//            "color":"#173177"
//        }
//    }

}
