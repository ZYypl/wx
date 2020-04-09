package com.example.demo.service;


import javax.servlet.ServletInputStream;
import java.util.Map;

public interface WxService {
    boolean check(String timestamp, String nonce, String signature);

    Map<String, String> parseRequest(ServletInputStream inputStream);

    String getResponse(Map<String, String> map);

    String getToken();

}
