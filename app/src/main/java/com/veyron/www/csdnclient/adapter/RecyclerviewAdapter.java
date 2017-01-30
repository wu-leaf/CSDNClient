package com.veyron.www.csdnclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.veyron.www.csdnclient.R;
import com.veyron.www.csdnclient.entity.NewsItem;

import java.util.List;

/**
 * Created by Veyron on 2017/1/29.
 * Function：给各个Fragment中使用的recyclerview准备的的适配器
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private Context context;
    private List<NewsItem> list;

    public void addAll(List<NewsItem> list) {
        this.list.addAll(list);
    }

    public List<NewsItem> getList(){
        return list;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    private OnItemClickListener onItemClickListener;

    public RecyclerviewAdapter(Context context, List<NewsItem> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 0 : list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent,
                    false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_footer, parent,
                    false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            NewsItem newsItem = list.get(position);
            ((ItemViewHolder) holder).title.setText(newsItem.getTitle());
            ((ItemViewHolder) holder).content.setText(newsItem.getContent());
            ((ItemViewHolder) holder).date.setText(newsItem.getDate());

            if (newsItem.getImgLink() != null) {
                ((ItemViewHolder) holder).img.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(newsItem.getImgLink())
                        .into(((ItemViewHolder) holder).img);
            } else {
                ((ItemViewHolder) holder).img.setVisibility(View.GONE);
            }

            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title, content, date; // 标题、内容、时间
        ImageView img; // 图片
        public ItemViewHolder(View view) {
            super(view);
            content = (TextView) view.findViewById(R.id.tv_item_content);
            title = (TextView) view.findViewById(R.id.tv_item_title);
            date = (TextView) view.findViewById(R.id.tv_item_date);
            img = (ImageView) view.findViewById(R.id.iv_item_img);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {
        public FootViewHolder(View view) {
            super(view);
        }
    }

}
