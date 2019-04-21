package com.baidu.geek.bean;

/**
 * Created by Administrator on 2019/4/19.
 */

public class V2exFragmentBean {
    public String url;
    public String pinglunshu;
    public String text;
    public String pinglunren;
    public String zuozhe;
    public String two;
    public String huifu;
    public String pinglunurl;

    public V2exFragmentBean(String url, String pinglunshu, String text, String pinglunren, String zuozhe, String two, String huifu, String pinglunurl) {
        this.url = url;
        this.pinglunshu = pinglunshu;
        this.text = text;
        this.pinglunren = pinglunren;
        this.zuozhe = zuozhe;
        this.two = two;
        this.huifu = huifu;
        this.pinglunurl = pinglunurl;
    }

    @Override
    public String toString() {
        return "V2exFragmentBean{" +
                "url='" + url + '\'' +
                ", pinglunshu='" + pinglunshu + '\'' +
                ", text='" + text + '\'' +
                ", pinglunren='" + pinglunren + '\'' +
                ", zuozhe='" + zuozhe + '\'' +
                ", two='" + two + '\'' +
                ", huifu='" + huifu + '\'' +
                ", pinglunurl='" + pinglunurl + '\'' +
                '}';
    }
}
