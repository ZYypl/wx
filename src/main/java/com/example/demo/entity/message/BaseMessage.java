package com.example.demo.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * com.example.demo.entity
 *
 * @author ypl
 * @create 2020-04-08 18:36
 */
public class BaseMessage {

    @XStreamAlias("FromUserName")
    private String fromUserName;

    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamAlias("CreateTime")
    private String createTime;

    @XStreamAlias("MsgType")
    private String msgType;

    public BaseMessage() {

    }
    public BaseMessage(Map<String ,String> map) {
        this.toUserName=map.get("FromUserName");
        this.fromUserName=map.get("ToUserName");
        this.createTime=System.currentTimeMillis()/1000+"";

    }


    public BaseMessage(String fromUserName, String toUserName, String createTime, String msgType) {
        this.fromUserName = fromUserName;
        this.toUserName = toUserName;
        this.createTime = createTime;
        this.msgType = msgType;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
