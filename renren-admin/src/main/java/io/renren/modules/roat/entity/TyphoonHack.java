package io.renren.modules.roat.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("typhoonHack")
public class TyphoonHack extends Model<TyphoonHack> {
    String hackLevel;
    String dongbei;
    String dongnan;
    String xinan;
    String xibei;
    String pinfoid;

    public TyphoonHack(String hackLevel, String dongbei, String dongnan, String xinan, String xibei, String pinfoid) {
        this.hackLevel = hackLevel;
        this.dongbei = dongbei;
        this.dongnan = dongnan;
        this.xinan = xinan;
        this.xibei = xibei;
        this.pinfoid = pinfoid;
    }

    public String getHackLevel() {
        return hackLevel;
    }

    public void setHackLevel(String hackLevel) {
        this.hackLevel = hackLevel;
    }

    public String getDongbei() {
        return dongbei;
    }

    public void setDongbei(String dongbei) {
        this.dongbei = dongbei;
    }

    public String getDongnan() {
        return dongnan;
    }

    public void setDongnan(String dongnan) {
        this.dongnan = dongnan;
    }

    public String getXinan() {
        return xinan;
    }

    public void setXinan(String xinan) {
        this.xinan = xinan;
    }

    public String getXibei() {
        return xibei;
    }

    public void setXibei(String xibei) {
        this.xibei = xibei;
    }

    public String getPinfoid() {
        return pinfoid;
    }

    public void setPinfoid(String pinfoid) {
        this.pinfoid = pinfoid;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
