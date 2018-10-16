package io.renren.modules.roat.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("tyMessage")
public class TyMessage extends Model<TyMessage> implements Serializable{
    public String content;
    public String title;
    public String time;
    public String video;
    public String images;
    public String typid;

    public String getTypid() {
        return typid;
    }

    public void setTypid(String typid) {
        this.typid = typid;
    }

    public TyMessage(){

    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public TyMessage(String content, String title, String time, String video, String images,String typid) {
        this.content = content;
        this.title = title;
        this.time = time;
        this.video = video;
        this.images = images;
        this.typid = typid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
