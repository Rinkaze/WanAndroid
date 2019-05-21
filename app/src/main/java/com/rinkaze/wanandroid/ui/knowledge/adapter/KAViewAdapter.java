package com.rinkaze.wanandroid.ui.knowledge.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.official.FeedArticleListData;

import java.util.List;

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
    public void onBindViewHolder(@NonNull KAViewAdapter.ViewHolder viewHolder, final int i) {
        final FeedArticleListData.DataBean.DatasBean datasBean = mList.get(i);
        viewHolder.tv_author.setText(mList.get(i).getAuthor());
        viewHolder.tv_niceDate.setText(mList.get(i).getNiceDate());
        viewHolder.tv_title.setText(mList.get(i).getTitle());
        viewHolder.tv_superChapterName.setText(mList.get(i).getSuperChapterName()+"/"+mList.get(i).getAuthor());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onKAClick!=null){
                    onKAClick.setonKAItemClick(datasBean,i);
                }
            }
        });
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
            tv_author=itemView.findViewById(R.id.tv_author);
            tv_niceDate=itemView.findViewById(R.id.tv_niceDate);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_superChapterName=itemView.findViewById(R.id.tv_superChapterName);
            img_follow=itemView.findViewById(R.id.img_follow);
        }
    }
    public interface onKAClick{
        void setonKAItemClick(FeedArticleListData.DataBean.DatasBean datasBean,int postion);
    }
    onKAClick onKAClick;

    public void setOnKAClick(KAViewAdapter.onKAClick onKAClick) {
        this.onKAClick = onKAClick;
    }
}
