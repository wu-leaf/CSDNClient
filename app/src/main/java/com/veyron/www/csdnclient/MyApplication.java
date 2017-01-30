package com.veyron.www.csdnclient;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Veyron on 2017/1/29.
 * Function：
 */
public class MyApplication extends Application {
    private static Context context;//全局的上下文
    private static Handler mainHandler;//全局的主线程handler
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化COntext
        context = this;
        //初始化mainHandler,在主线程创建的handler就是主线程的handler
        mainHandler = new Handler();
    }

    /**
     * 获取全局的上下文
     * @return
     */
    public static Context getContext(){
        return context;
    }

    public static Handler getMainHandler(){
        return mainHandler;
    }
}
