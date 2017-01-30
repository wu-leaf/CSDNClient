package com.veyron.www.csdnclient.entity;

/**
 * Created by Veyron on 2017/1/29.
 * Function：新闻实体类
 */
public class NewsItem {

    private String title; // 标题
    private String link; // 链接
    private String date; // 发布日期
    private String imgLink; // 图片的链接
    private String content; // 摘要
    private int newsType; // 类型
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNewsType() {
        return newsType;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", date='" + date + '\'' +
                ", imgLink='" + imgLink + '\'' +
                ", content='" + content + '\'' +
                ", newsType=" + newsType +
                ", id=" + id +
                '}';
    }
}
