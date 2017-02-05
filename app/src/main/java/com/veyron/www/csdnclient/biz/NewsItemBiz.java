package com.veyron.www.csdnclient.biz;



import com.veyron.www.csdnclient.entity.NewsItem;
import com.veyron.www.csdnclient.util.HtmlUtil;
import com.veyron.www.csdnclient.util.URLUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Veyron on 2017/1/29.
 * Function：解析新闻类
 */

public class NewsItemBiz {
    /**
     * 获取新闻Item
     *
     * @param newsType    文章类型
     * @param currentPage 当前页码
     * @return
     * @throws Exception
     */
    public List<NewsItem> getNewsItems(int newsType, int currentPage) throws IOException {

        String urlStr = URLUtil.getNewsUrl(newsType, currentPage);
        String htmlStr = HtmlUtil.doGet(urlStr);

        List<NewsItem> newsItems = new ArrayList<NewsItem>();
        NewsItem newsItem = null;

        /**
         * Jsoup只需先构建一个Document对象，然后就可以像使用js一个解析html了
         */
        // String作为输入源
        Document doc = Jsoup.parse(htmlStr);
        // 通过class获得元素
        Elements units = doc.getElementsByClass("unit");//找到所以unit标签
        for (int i = 0; i < units.size(); i++) { //遍历units集合
            newsItem = new NewsItem();
            newsItem.setNewsType(newsType);

          // Element是用来构建xml中节点的
            Element unit_ele = units.get(i);//遍历units集合
            Element h1_ele = unit_ele.getElementsByTag("h1").get(0);//拿到第一个h1标签元素
            Element h1_a_ele = h1_ele.child(0);//h1标签的子标签---a 标签
            String title = h1_a_ele.text();//a标签里面的text---标题
            String href = h1_a_ele.attr("href");//拿到a标签里面的href属性---文章超链接

            newsItem.setLink(href);//设置文章超链接
            newsItem.setTitle(title);//设置文章标题

            Element h4_ele = unit_ele.getElementsByTag("h4").get(0);//拿到unit标签下的第一个h4标签
            Element ago_ele = h4_ele.getElementsByClass("ago").get(0);//拿到h4标签下的class属性为ago的元素
            String date = ago_ele.text();//拿到class属性为ago标签的内容

            newsItem.setDate(date);//设置文章的时间

            Element dl_ele = unit_ele.getElementsByTag("dl").get(0);// dl
            Element dt_ele = dl_ele.child(0);// dt
            try {// 可能没有图片
                Element img_ele = dt_ele.child(0);
                String imgLink = img_ele.child(0).attr("src");
                newsItem.setImgLink(imgLink);
            } catch (IndexOutOfBoundsException e) {

            }
            Element content_ele = dl_ele.child(1);// dd
            String content = content_ele.text();
            newsItem.setContent(content);//设置摘要
            newsItems.add(newsItem);
        }
        return newsItems;
    }

}