package com.veyron.www.csdnclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.veyron.www.csdnclient.R;
import com.veyron.www.csdnclient.entity.NewsItem;

import java.util.List;

/**
 * Created by Veyron on 2017/1/29.
 * Function：使用listview的适配器，本案例中没有使用该适配器
 */
public class ListviewAdapter extends BaseAdapter {

    private Context context;
    private List<NewsItem> list;
    private ViewHolder holder;
    private LayoutInflater inflater;

    public ListviewAdapter(Context context, List<NewsItem> list){
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void addAll(List<NewsItem> list) {
        this.list.addAll(list);
    }

    public List<NewsItem> getList(){
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list, null);
            holder = new ViewHolder();
            holder.content = (TextView) convertView.findViewById(R.id.tv_item_content);
            holder.title = (TextView) convertView.findViewById(R.id.tv_item_title);
            holder.date = (TextView) convertView.findViewById(R.id.tv_item_date);
            holder.img = (ImageView) convertView.findViewById(R.id.iv_item_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsItem newsItem = list.get(position);
        holder.title.setText(newsItem.getTitle());
        holder.content.setText(newsItem.getContent());
        holder.date.setText(newsItem.getDate());

        if (newsItem.getImgLink() != null) {
            holder.img.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(newsItem.getImgLink())
                    .into(holder.img);
        } else {
            holder.img.setVisibility(View.GONE);
        }

        return convertView;
    }

    private class ViewHolder {
        TextView title; // 标题
        TextView content; // 内容
        ImageView img; // 图片
        TextView date; // 时间
    }

}
