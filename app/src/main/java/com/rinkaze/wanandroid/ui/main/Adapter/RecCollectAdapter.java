package com.rinkaze.wanandroid.ui.main.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.bean.MyCollectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 灵风 on 2019/5/23.
 */

public class RecCollectAdapter extends RecyclerView.Adapter<RecCollectAdapter.ViewHolder> {

    private Context context;
    private List<MyCollectBean.DataEntity.DatasEntity> list;

    public RecCollectAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(List<MyCollectBean.DataEntity.DatasEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(context,R.layout.item_collect,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        MyCollectBean.DataEntity.DatasEntity entity = list.get(i);
        holder.tvAuthor.setText(entity.getAuthor());
        holder.tvChapter.setText(entity.getChapterName());
        holder.tvDate.setText(entity.getNiceDate());
        holder.tvTitle.setText(entity.getTitle());
        holder.ivCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCollectListener != null){
                    onCollectListener.disCollect(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAuthor;
        TextView tvTitle;
        TextView tvChapter;
        TextView tvDate;
        ImageView ivCollect;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvChapter = itemView.findViewById(R.id.tv_chapter);
            tvDate = itemView.findViewById(R.id.tv_date);
            ivCollect = itemView.findViewById(R.id.iv_collect);
        }
    }

    private OnCollectListener onCollectListener;

    public void setOnCollectListener(OnCollectListener onCollectListener) {
        this.onCollectListener = onCollectListener;
    }

    public interface OnCollectListener{
        void disCollect(int position);
    }
}
