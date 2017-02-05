package com.veyron.www.csdnclient.util;

/**
 * Created by Veyron on 2017/1/29.
 * Function：根据CSDN资讯页面特性写的生成网址的工具类
 */
public class URLUtil {

    public static final int NEWS_TYPE_NEWS = 1; // 业界
    public static final int NEWS_TYPE_MOBILE = 2; // 移动开发
    public static final int NEWS_TYPE_CLOUD = 3; // 云计算
    public static final int NEWS_TYPE_SD = 4; // 软件研发

    public static final String TEST ="http://www.csdn.net/article/2017-01-22/2826689";
    public static final String BASE_URL_NEWS = "http://news.csdn.net/news/"; // 业界
    public static final String BASE_URL_MOBILE = "http://mobile.csdn.net/mobile/"; // 移动开发
    public static final String BASE_URL_CLOUD = "http://cloud.csdn.net/cloud/"; // 云计算
    public static final String BASE_URL_SD = "http://sd.csdn.net/sd/"; // 软件研发

    /**
     * 根据文章类型和当前页码生成url
     *
     * @param newsType    文章类型
     * @param currentPage 当前页码
     * @return
     */
    public static String getNewsUrl(int newsType, int currentPage) {
        currentPage = currentPage > 0 ? currentPage : 1;
        String urlStr = "";
        switch (newsType) {
            case NEWS_TYPE_NEWS:
                urlStr = BASE_URL_NEWS;
                break;
            case NEWS_TYPE_MOBILE:
                urlStr = BASE_URL_MOBILE;
                break;
            case NEWS_TYPE_CLOUD:
                urlStr = BASE_URL_CLOUD;
                break;
            case NEWS_TYPE_SD:
                urlStr = BASE_URL_SD;
                break;
            default:
                break;
        }
        urlStr += currentPage; // URL = BASE_URL + 当前页码   生成最终的url
        return urlStr;
    }

}
