package com.veyron.www.csdnclient.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.veyron.www.csdnclient.R;
import com.veyron.www.csdnclient.adapter.RecyclerviewAdapter;
import com.veyron.www.csdnclient.biz.NewsItemBiz;
import com.veyron.www.csdnclient.entity.NewsItem;
import com.veyron.www.csdnclient.ui.activity.ContentActivity;
import com.veyron.www.csdnclient.util.HtmlUtil;
import com.veyron.www.csdnclient.util.URLUtil;
import com.veyron.www.csdnclient.view.RecycleViewDivider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Veyron on 2017/1/29.
 * Function：业界资讯栏目
 */
public class NewsFragment extends Fragment {


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private boolean isLoading = false;//判断是否加载更多，避免重复请求网络
    private int currentPage = 1;
    private List<NewsItem> mList = new ArrayList<NewsItem>();//装载的数据

    private RecyclerviewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_news,container,false);
      mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.srl_news);
      mRecyclerView = (RecyclerView)view.findViewById(R.id.rv_news);
      //添加分割线
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), R.drawable.divider_mileage));

      //设置进度动画的颜色
         mSwipeRefreshLayout.setColorSchemeResources(R.color.swipe_color_1);
      //设置进度圈的大小：默认 或 大
         mSwipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
      //设置下拉缩放动画效果以及下拉的高度
         mSwipeRefreshLayout.setProgressViewEndTarget(true, 200);

      //创建一个线性布局管理器
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        //一加载页面就获取数据
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        });

        //设置适配器
        adapter = new RecyclerviewAdapter(getContext(),mList);
        mRecyclerView.setAdapter(adapter);


        // 设置手势滑动监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 下拉刷新操作
                getData();
            }
        });
        // 检测滑动事件
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            // dx:在x方向滑动的值，这个值有正负, dy:在y方向滑动的值，这个值有正负
            // dx>0:右滑, dx<0:左滑, dy<0:上滑, dy>0:下滑
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 当前屏幕所看到的子项个数
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    //已经滑动到最下面
                    boolean isRefreshing = mSwipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        // 这里使用postDelayed()方法模拟网络请求等延时操作,实际开发可去掉postDelayed()方法
                        mSwipeRefreshLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //开始加载更多
                                isLoading = true;
                                loadMoreData();
                            }
                        }, 1000);
                    }
                }
            }
        });
        //添加点击事件
        adapter.setOnItemClickListener(new RecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("url", adapter.getList().get(position).getLink());
                intent.putExtra("title","业界资讯");
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });

        return view;
    }

    /**
     * 获取网络数据
     */
    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mList = new NewsItemBiz().getNewsItems(URLUtil.NEWS_TYPE_NEWS, currentPage);
                    mHandler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
   private Handler mHandler = new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           switch(msg.what){
               case 1:
                   adapter.addAll(mList);
                   adapter.notifyDataSetChanged();
                   isLoading = false;
                   //请求完成结束刷新状态
                   mSwipeRefreshLayout.setRefreshing(false);
                   break;
           }
       }
   };
    /**
     * 加载更多操作
     */
    private void loadMoreData() {
        currentPage++;
        mList.clear();
        getData();
    }
}
