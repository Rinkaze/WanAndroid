package com.rinkaze.wanandroid.ui.project.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.ProjectListBean;
import com.rinkaze.wanandroid.utils.GlideUtil;
import com.rinkaze.wanandroid.utils.SpUtil;
import com.rinkaze.wanandroid.utils.ToastUtil;

import java.util.ArrayList;

public class RlvProjectClassifyAdapter extends RecyclerView.Adapter {
    private FragmentActivity mContext;
    private ArrayList<ProjectListBean.DataBean.DatasBean> mList;
    private OnItemClickListener mListener;
    private ProjectListBean.DataBean.DatasBean datasBean;
    private int num;
    public RlvProjectClassifyAdapter(FragmentActivity activity, ArrayList<ProjectListBean.DataBean.DatasBean> list) {
        this.mContext = activity;
        this.mList = list;
    }

    public void setmList(ArrayList<ProjectListBean.DataBean.DatasBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_project, null, false);
        return new ProjectViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        final ProjectViewHolder viewHolders = (ProjectViewHolder) viewHolder;
         datasBean = mList.get(i);
        String envelopePic = datasBean.getEnvelopePic();
        String title = datasBean.getTitle();
        String desc = datasBean.getDesc();
        String author = datasBean.getAuthor();
        String niceDate = datasBean.getNiceDate();
        GlideUtil.loadImage(R.mipmap.ic_launcher, envelopePic, viewHolders.mIv, mContext);

        GlideUtil.loadImage(R.mipmap.zhanweitu_home_kapian_hdpi, envelopePic, viewHolders.mIv, mContext);

        viewHolders.mTitles.setText(title);
        viewHolders.mSizetitle.setText(desc);
        viewHolders.mYingwen.setText(author);
        viewHolders.mTime.setText(niceDate);

        //webview
        viewHolders.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(datasBean.getLink(),datasBean.getTitle(),datasBean.getAuthor(), i);
                }
            }
        });
        //小心心
        viewHolders.mCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datasBean.isCollect()) {
                    if ((boolean) SpUtil.getParam(Constants.LOGIN, false)) {
                        Glide.with(mContext).load(R.mipmap.follow_unselected).into(viewHolders.mCollect);
                        datasBean.setCollect(false);
                    }
                    onItemUrl.setDislike(num);
                } else {
                    if ((boolean) SpUtil.getParam(Constants.LOGIN, false)) {
                        Glide.with(mContext).load(R.mipmap.follow).into(viewHolders.mCollect);
                        datasBean.setCollect(true);
                    }
                    onItemUrl.setLike(num);
                }
            }
        });

        viewHolders.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                  mListener.onItemClick(datasBean.getLink(),datasBean.getTitle(),datasBean.getAuthor(),i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIv;
        private final TextView mTitles;
        private final TextView mSizetitle;
        private final TextView mYingwen;
        private final TextView mTime;
        private final ImageView mCollect;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.project_img);
            mTitles = itemView.findViewById(R.id.project_title);
            mSizetitle = itemView.findViewById(R.id.project_name);
            mYingwen = itemView.findViewById(R.id.project_author);
            mTime = itemView.findViewById(R.id.project_nicedata);
            mCollect = itemView.findViewById(R.id.project_follow);

        }
    }
    //接口回调
    //1.写个接口
    public interface OnItemClickListener {
        void onItemClick(String link,String title,String author, int position);

    }
    //2.写个方法,将OnItemClickListener设置到Adapter中
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
    private OnItemUrl onItemUrl;

    public void setOnItemUrl(OnItemUrl onItemUrl) {
        this.onItemUrl = onItemUrl;
    }
    public interface OnItemUrl {
        void setLike(int position);

        void setDislike(int id);
    }
}
