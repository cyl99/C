package com.baidu.geek.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/4/19.
 */

public class GoldShowBean implements Serializable{
    public String title;
    public boolean ischecked;

    public GoldShowBean(String title, boolean ischecked) {
        this.title = title;
        this.ischecked = ischecked;
    }
}
