package com.example.demo.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * com.example.demo.entity
 *
 * @author ypl
 * @create 2020-04-08 22:06
 */
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {

    @XStreamAlias("MediaId")
    private  String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public ImageMessage() {
    }

    public ImageMessage(Map<String, String> map,String mediaId) {
        super(map);
        this.setMsgType("image");
        this.mediaId=mediaId;
    }
}
