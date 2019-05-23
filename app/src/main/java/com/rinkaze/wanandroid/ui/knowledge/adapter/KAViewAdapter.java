package com.rinkaze.wanandroid.ui.knowledge.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.official.FeedArticleListData;

import java.util.List;

import retrofit2.http.POST;

public class KAViewAdapter extends RecyclerView.Adapter<KAViewAdapter.ViewHolder> {
    private Context context;
    private List<FeedArticleListData.DataBean.DatasBean> mList;

    public KAViewAdapter(Context context, List<FeedArticleListData.DataBean.DatasBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setmList(List<FeedArticleListData.DataBean.DatasBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public KAViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_knrview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final KAViewAdapter.ViewHolder viewHolder, final int i) {
        final FeedArticleListData.DataBean.DatasBean datasBean = mList.get(i);
        if (datasBean != null) {


            viewHolder.tv_author.setText(mList.get(i).getAuthor());
            viewHolder.tv_niceDate.setText(mList.get(i).getNiceDate());
            viewHolder.tv_title.setText(mList.get(i).getTitle());
            viewHolder.tv_superChapterName.setText(mList.get(i).getSuperChapterName() + "/" + mList.get(i).getAuthor());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onKAClick != null) {
                        onKAClick.setonKAItemClick(datasBean, i);
                    }
                }
            });
            //心形
            viewHolder.img_follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mList.get(i).isCollect()) {
                        like.remove(mList.get(i).getId());
                        Glide.with(context)
                                .load(R.mipmap.follow_unselected)
                                .into(viewHolder.img_follow);
                        mList.get(i).setCollect(false);
                    } else {
                        like.like(mList.get(i).getId());
                        Glide.with(context)
                                .load(R.mipmap.follow)
                                .into(viewHolder.img_follow);
                        mList.get(i).setCollect(true);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_author;
        private TextView tv_niceDate;
        private TextView tv_title;
        private TextView tv_superChapterName;
        private ImageView img_follow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_author = itemView.findViewById(R.id.tv_author);
            tv_niceDate = itemView.findViewById(R.id.tv_niceDate);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_superChapterName = itemView.findViewById(R.id.tv_superChapterName);
            img_follow = itemView.findViewById(R.id.img_follow);
        }
    }

    public interface onKAClick {
        void setonKAItemClick(FeedArticleListData.DataBean.DatasBean datasBean, int postion);
    }

    onKAClick onKAClick;

    public void setOnKAClick(KAViewAdapter.onKAClick onKAClick) {
        this.onKAClick = onKAClick;
    }

    //心形
    public interface OnItenClickListener {
        void like(int id);//收藏

        void remove(int id);//不收藏
    }

    private OnItenClickListener like;

    public void setLike(OnItenClickListener like) {
        this.like = like;
    }

}
