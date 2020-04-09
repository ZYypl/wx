package com.example.demo.util;

import com.example.demo.entity.AccessToken;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * com.example.demo.util
 *
 *
 * @author ypl
 * @create 2020-04-09 14:19
 */
@Service
public class GetTokenUtil {


    private static  String GET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";


    private static  String APPID="wx2715946ded628905";


    private static  String APPSECRET="f31768995141f639eaa8546409f49566";

    private static  AccessToken at;


    //指定地址发送get请求
    public static String getFromUrl(String url){

        try {
            URL url1 = new URL(url);
            URLConnection urlConnection = url1.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            byte[] bytes= new byte[1024];
            int len ;
            StringBuilder s = new StringBuilder();
            while((len=inputStream.read(bytes))!=-1){
                s.append(new String(bytes,0,len));
            }
            return s.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    //获取token
    private static void  getAccessToken(){
        String url = GET_TOKEN_URL
                .replace("APPID",APPID)
                .replace("APPSECRET",APPSECRET);
        String token = GetTokenUtil.getFromUrl(url);//获取token

        JSONObject jsonObject = JSONObject.fromObject(token);
        String access_token = jsonObject.getString("access_token");
        String expires_in = jsonObject.getString("expires_in");

        at = new AccessToken(access_token,expires_in);

    }

    public static String getToken(){
        if(at==null||at.isExpired()){
            getAccessToken();
        }
        return at.getAccessToken();
    }


    //向指定地址发送post请求
    public static String postFromUrl(String url,String data){

        try {
            URL url1 = new URL(url);
            URLConnection urlConnection = url1.openConnection();
            urlConnection.setDoOutput(true);

            OutputStream outputStream = urlConnection.getOutputStream();//写数据
            outputStream.write(data.getBytes());
            outputStream.close();

            InputStream inputStream = urlConnection.getInputStream(); //读返回数据
            byte[] bytes= new byte[1024];
            int len ;
            StringBuilder s = new StringBuilder();
            while((len=inputStream.read(bytes))!=-1){
                s.append(new String(bytes,0,len));
            }
            return s.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;

    }




    /**
     * 获取带参数二维码的ticket
     * @return
     */
    public static String getQrCodeTicket() {

        String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+getToken();
        //生成临时字符二维码
        String data="{\"expire_seconds\": 600, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"llzs\"}}}";
        String result = postFromUrl(url, data);
        String ticket = JSONObject.fromObject(result).getString("ticket");
        return ticket;
    }

}
