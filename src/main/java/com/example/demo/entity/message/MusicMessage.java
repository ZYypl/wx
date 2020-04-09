package com.example.demo.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * com.example.demo.entity
 *
 * @author ypl
 * @create 2020-04-08 22:13
 */
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage {


    private Music music;

    public MusicMessage() {
    }

    public MusicMessage(Map<String, String> map ,Music music) {
        super(map);
        this.setMsgType("music");
        this.music=music;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
