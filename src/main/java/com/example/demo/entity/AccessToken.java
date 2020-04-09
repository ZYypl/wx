package com.example.demo.entity;

/**
 * com.example.demo.entity
 *
 * @author ypl
 * @create 2020-04-09 14:40
 */
public class AccessToken {


    //接口返回值
    private String accessToken;
    private String expiresIn;//多长时间过期。，默认两个小时7200s


    private Long expireTime;//过期时间


    public AccessToken() {
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public AccessToken(String accessToken,  String expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        //根据返回值计算出过期时间
        this.expireTime = System.currentTimeMillis()+Integer.valueOf(expiresIn)*1000;
    }

    //判断token是否过期
    public boolean  isExpired(){
        return System.currentTimeMillis()>expireTime;
    }
}
