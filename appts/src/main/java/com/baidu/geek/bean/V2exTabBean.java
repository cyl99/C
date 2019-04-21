package com.baidu.geek.bean;

/**
 * Created by Administrator on 2019/4/19.
 */

public class V2exTabBean {
    public String key;
    public  String tab;

    public V2exTabBean(String key, String tab) {
        this.key = key;
        this.tab = tab;
    }

    @Override
    public String toString() {
        return "V2exTabBean{" +
                "key='" + key + '\'' +
                ", tab='" + tab + '\'' +
                '}';
    }
}
