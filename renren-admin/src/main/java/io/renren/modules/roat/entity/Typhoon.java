package io.renren.modules.roat.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("typhoon")
public class Typhoon extends Model<Typhoon> implements Serializable{
    public int tid;
    public String tname;
    public String tnamecn;
    public String jianjie;
    public String status;

    public Typhoon(int tid,String tname,String tnamecn,String jianjie,String status){
        this.tid = tid;
        this.tname = tname;
        this.tnamecn = tnamecn;
        this.jianjie = jianjie;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Typhoon{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tnamecn='" + tnamecn + '\'' +
                ", jianjie='" + jianjie + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
