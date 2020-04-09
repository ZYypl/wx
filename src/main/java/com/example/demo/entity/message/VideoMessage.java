package com.example.demo.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * com.example.demo.entity
 *
 * @author ypl
 * @create 2020-04-08 22:10
 */
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage {

    @XStreamAlias("MediaId")
    private String mediaId;

    @XStreamAlias("Title")
    private String title;

    @XStreamAlias("Description")
    private String description;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VideoMessage() {
    }

    public VideoMessage(Map<String, String> map,String mediaId,String title,String description) {
        super(map);
        this.setMsgType("video");
        this.mediaId=mediaId;
        this.title=title;
        this.description=description;
    }
}
