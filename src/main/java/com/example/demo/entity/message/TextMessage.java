package com.example.demo.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * com.example.demo.entity
 *
 * @author ypl
 * @create 2020-04-08 21:56
 */
@XStreamAlias("xml")
public class TextMessage  extends BaseMessage{


    @XStreamAlias("Content")
    private  String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextMessage() {
    }

    public TextMessage(Map<String ,String> map,String content) {
        super(map);
        this.setMsgType("text");//设置文本type
        this.content=content;
    }
}
