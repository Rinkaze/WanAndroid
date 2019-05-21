package com.rinkaze.wanandroid.ui.project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.bean.ProjectListBean;
import com.rinkaze.wanandroid.utils.GlideUtil;
import com.rinkaze.wanandroid.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

    private Context context;
    private List<ProjectListBean.DataBean.DatasBean> list;

    public ProjectListAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(List<ProjectListBean.DataBean.DatasBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProjectListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_project, null));
    }

    @Override
    public void onBindViewHolder(@NonNull final ProjectListAdapter.ViewHolder holder, final int position) {
        GlideUtil.loadUrlRoundImg(6, R.mipmap.zhanweitu_home_kapian_hdpi,
                list.get(position).getEnvelopePic(), holder.img, context);
        if (list.get(position).isCollect()) {
            GlideUtil.loadImage(R.mipmap.zhanweitu_home_kapian_hdpi,
                    R.mipmap.project_follow_hong, holder.ivFollow, context);
        } else {
            GlideUtil.loadImage(R.mipmap.zhanweitu_home_kapian_hdpi, R.mipmap.project_follow, holder.ivFollow, context);
        }
        holder.tvName.setText(list.get(position).getChapterName());
        holder.title.setText(list.get(position).getTitle());
        holder.nicedata.setText(list.get(position).getNiceDate());
        holder.ivFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).isCollect()) {
                    if (onFollowCliclListener != null) {
                        ToastUtil.showShort("已取消关注");
                        onFollowCliclListener.cancelFollow(list.get(position).getId());
                        Glide.with(context).load(R.mipmap.project_follow).into(holder.ivFollow);
                        list.get(position).setCollect(false);
                    }
                } else {
                    if (onFollowCliclListener != null) {
                        ToastUtil.showShort("已关注");
                        onFollowCliclListener.onFollow(list.get(position).getId());
                        Glide.with(context).load(R.mipmap.project_follow_hong).into(holder.ivFollow);
                        list.get(position).setCollect(true);
                    }
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(list.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView tvName;
        TextView nicedata;
        TextView title;
        ImageView ivFollow;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.project_img);
            tvName = itemView.findViewById(R.id.project_name);
            nicedata = itemView.findViewById(R.id.project_nicedata);
            title = itemView.findViewById(R.id.project_title);
            ivFollow = itemView.findViewById(R.id.project_follow);
        }
    }

    private OnFollowCliclListener onFollowCliclListener;

    public void setOnFollowCliclListener(OnFollowCliclListener onFollowCliclListener) {
        this.onFollowCliclListener = onFollowCliclListener;
    }

    public interface OnFollowCliclListener {
        void onFollow(int id);

        void cancelFollow(int id);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(ProjectListBean.DataBean.DatasBean entity);
    }
}
