package io.renren.modules.roat.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("article")
public class Article extends Model<Article> implements Serializable {
    public String year;
    public String typhoonName;
    public String url;
    public String typid;
    public Article(){

    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public Article(String year, String typhoonName, String url, String typid) {
        this.year = year;
        this.typhoonName = typhoonName;
        this.url = url;
        this.typid = typid;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTyphoonName() {
        return typhoonName;
    }

    public void setTyphoonName(String typhoonName) {
        this.typhoonName = typhoonName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTypid() {
        return typid;
    }

    public void setTypid(String typid) {
        this.typid = typid;
    }
}
