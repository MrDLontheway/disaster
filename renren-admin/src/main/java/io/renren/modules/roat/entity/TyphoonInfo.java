package io.renren.modules.roat.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("typhoonInfo")
public class TyphoonInfo extends Model<TyphoonInfo> implements Serializable {
    public String infoid;
    public String infotime;
    public String qiangdu;
    public String lat;
    public String lng;
    public String midqiya;
    public String maxsudu;
    public String fangxiang;
    public String yidongsudu;
    public int tid;

    public String getInfoid() {
        return infoid;
    }

    public void setInfoid(String infoid) {
        this.infoid = infoid;
    }

    public String getInfotime() {
        return infotime;
    }

    public void setInfotime(String infotime) {
        this.infotime = infotime;
    }

    public String getQiangdu() {
        return qiangdu;
    }

    public void setQiangdu(String qiangdu) {
        this.qiangdu = qiangdu;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getMidqiya() {
        return midqiya;
    }

    public void setMidqiya(String midqiya) {
        this.midqiya = midqiya;
    }

    public String getMaxsudu() {
        return maxsudu;
    }

    public void setMaxsudu(String maxsudu) {
        this.maxsudu = maxsudu;
    }

    public String getFangxiang() {
        return fangxiang;
    }

    public void setFangxiang(String fangxiang) {
        this.fangxiang = fangxiang;
    }

    public String getYidongsudu() {
        return yidongsudu;
    }

    public void setYidongsudu(String yidongsudu) {
        this.yidongsudu = yidongsudu;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public TyphoonInfo(int tid,String infoid, String infotime, String qiangdu, String lat, String lng, String midqiya, String maxsudu, String fangxiang, String yidongsudu) {
        this.tid = tid;
        this.infoid = infoid;
        this.infotime = infotime;
        this.qiangdu = qiangdu;
        this.lat = lat;
        this.lng = lng;
        this.midqiya = midqiya;
        this.maxsudu = maxsudu;
        this.fangxiang = fangxiang;
        this.yidongsudu = yidongsudu;
    }

    @Override
    public String toString() {
        return "TyphoonInfo{" +
                "infoid='" + infoid + '\'' +
                ", infotime='" + infotime + '\'' +
                ", qiangdu='" + qiangdu + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", midqiya='" + midqiya + '\'' +
                ", maxsudu='" + maxsudu + '\'' +
                ", fangxiang='" + fangxiang + '\'' +
                ", yidongsudu='" + yidongsudu + '\'' +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
