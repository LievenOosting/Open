package com.example.qiuchunjia.sample.bean;

import com.qcj.common.model.Entity;

/**
 * Created by qiuchunjia on 2016/4/22.
 */
public class DataModel extends Entity {


    /**
     * title : 三板企业上创新层显神通 监管方冷对如此闹腾
     * thumb : http://www.chinaipo.com/data/upload/2016/0421/10/571837bcc80d4_180_120.jpg
     * inputtime : 1461236430
     * updatetime : 1461204937
     * info : 21世纪经济报道·04-21
     * is_origin : 0
     * catid : 100001
     * cat_name : 行业动态
     * color-type : 1
     */
    private String title;
    private String thumb;
    private String inputtime;
    private String updatetime;
    private String info;
    private String is_origin;
    private String catid;
    private String cat_name;
    /**
     * color_type : 1
     */
    private int color_type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getInputtime() {
        return inputtime;
    }

    public void setInputtime(String inputtime) {
        this.inputtime = inputtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIs_origin() {
        return is_origin;
    }

    public void setIs_origin(String is_origin) {
        this.is_origin = is_origin;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getColor_type() {
        return color_type;
    }

    public void setColor_type(int color_type) {
        this.color_type = color_type;
    }
}
