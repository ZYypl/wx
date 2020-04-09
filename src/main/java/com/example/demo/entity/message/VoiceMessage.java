package com.example.demo.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * com.example.demo.entity
 *
 * @author ypl
 * @create 2020-04-08 22:08
 */

@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage {

    @XStreamAlias("MediaId")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public VoiceMessage() {
    }

    public VoiceMessage(Map<String, String> map ,String mediaId) {
        super(map);
        this.setMsgType("voice");
        this.mediaId= mediaId;
    }
}
