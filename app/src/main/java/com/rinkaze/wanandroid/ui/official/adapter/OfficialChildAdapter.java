package com.rinkaze.wanandroid.ui.official.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rinkaze.wanandroid.Bean.official.FeedArticleListData;
import com.rinkaze.wanandroid.R;

import java.nio.file.Path;
import java.util.ArrayList;

public class OfficialChildAdapter extends RecyclerView.Adapter<OfficialChildAdapter.ViewHolder> {
    private ArrayList<FeedArticleListData.DataBean.DatasBean> list;
    private Context context;

    public OfficialChildAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<FeedArticleListData.DataBean.DatasBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OfficialChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_official, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final OfficialChildAdapter.ViewHolder viewHolder, final int i) {
        FeedArticleListData.DataBean.DatasBean datasBean = list.get(i);
        viewHolder.tvName.setText(datasBean.getAuthor());
        viewHolder.tvTime.setText(datasBean.getNiceDate());
        viewHolder.tvTitle.setText(datasBean.getTitle());
        viewHolder.tvOfficial.setText(datasBean.getSuperChapterName() + "/" + datasBean.getAuthor());

        viewHolder.ivCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(i) != null) {
                    if (list.get(i).isFresh()) {
                        like.remove(list.get(i).getId());
                        Glide.with(context)
                                .load(R.mipmap.follow_unselected)
                                .into(viewHolder.ivCollect);
                        list.get(i).setFresh(false);

                    } else {
                        like.setLike(list.get(i).getId());
                        Glide.with(context)
                                .load(R.mipmap.follow)
                                .into(viewHolder.ivCollect);
                        list.get(i).setFresh(true);
                    }

                }
            }
        });


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.setClick(view,i);
            }
        });


//        if (list.get(position)!=null){
//
//            if (list.get(position).isIsFollowed()){
//                like.remove(list.get(position).getId());
//                Glide.with(context)
//                        .load(R.mipmap.follow_unselected)
//                        .into(holder.heart);
//                list.get(position).setIsFollowed(false);
//
//            }else {
//                like.setLike(list.get(position).getId());
//                Glide.with(context)
//                        .load(R.mipmap.follow)
//                        .into(holder.heart);
//                list.get(position).setIsFollowed(true);
//            }
//
//        }
//    }
//});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvTime;
        TextView tvTitle;
        TextView tvOfficial;
        ImageView ivCollect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_name);
            tvTime = itemView.findViewById(R.id.item_time);
            tvTitle = itemView.findViewById(R.id.item_title);
            tvOfficial = itemView.findViewById(R.id.item_official);
            ivCollect = itemView.findViewById(R.id.item_collect);
        }
    }

    public setClickLike like;
    public setOnClick click;

    public interface setOnClick {
        void setClick(View v, int position);
    }

    public void setClick(setOnClick click) {
        this.click = click;
    }

    public interface setClickLike {
        void setLike(int position);
        void remove(int id);

    }

    public void setLike(setClickLike like) {
        this.like = like;
    }

}
