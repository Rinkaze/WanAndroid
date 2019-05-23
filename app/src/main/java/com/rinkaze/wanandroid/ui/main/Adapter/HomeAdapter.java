package com.rinkaze.wanandroid.ui.main.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.HomeBanner;
import com.rinkaze.wanandroid.bean.HomeBean;
import com.rinkaze.wanandroid.utils.SpUtil;
import com.rinkaze.wanandroid.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomeBean.DataBean.DatasBean> listitem;
    private List<HomeBanner.DataBean> listBann;
    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;
    Context context;
    private int num;

    public HomeAdapter(Context context) {
        this.context = context;
        listBann = new ArrayList<>();
        listitem = new ArrayList<>();
    }

    public void setListitem(List<HomeBean.DataBean.DatasBean> listitem) {
        this.listitem = listitem;
        notifyDataSetChanged();
    }

    public void setListBann(List<HomeBanner.DataBean> listBann) {
        this.listBann = listBann;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new MyBanner(LayoutInflater.from(context).inflate(R.layout.banner_home, null));
        } else {
            return new MyView(LayoutInflater.from(context).inflate(R.layout.item_home, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == 1) {
            final MyBanner myBanner = (MyBanner) viewHolder;
            myBanner.banner.setImages(listBann);
            mBannerTitleList = new ArrayList<>();
            for (HomeBanner.DataBean dataBean : listBann) {
                mBannerTitleList.add(dataBean.getTitle());
            }

            myBanner.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    HomeBanner.DataBean dataBean = (HomeBanner.DataBean) path;
                    Glide.with(context).load(dataBean.getImagePath()).into(imageView);
                }
            }).setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            myBanner.banner.setBannerTitles(mBannerTitleList);
            myBanner.banner.setDelayTime(3000);
            myBanner.banner.start();
        } else {
            final MyView myView = (MyView) viewHolder;
            int newPosition = i;
            if (listBann != null && listBann.size() > 0) {
                newPosition = i - 1;
                num = newPosition;
            }
            final HomeBean.DataBean.DatasBean datasBean = listitem.get(newPosition);
            myView.name.setText(datasBean.getAuthor());
            myView.chapter.setText(datasBean.getSuperChapterName() + "/");
            myView.data.setText(datasBean.getNiceDate());
            myView.superCh.setText(datasBean.getChapterName());
            myView.title.setText(datasBean.getTitle());

            myView.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemUrl.onClick(num);
                }
            });
            myView.collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (datasBean.isCollect()) {
                        if ((boolean) SpUtil.getParam(Constants.LOGIN, false)) {
                            Glide.with(context).load(R.mipmap.follow_unselected).into(myView.collect);
                            datasBean.setCollect(false);
                        }
                        onItemUrl.setDislike(num);
                    } else {
                        if ((boolean) SpUtil.getParam(Constants.LOGIN, false)) {
                            Glide.with(context).load(R.mipmap.follow).into(myView.collect);
                            datasBean.setCollect(true);
                        }
                        onItemUrl.setLike(num);
                    }
                }
            });


        }
    }


    @Override
    public int getItemCount() {
        if (listBann != null && listBann.size() > 0) {
            return listitem.size() + 1;
        }
        return listitem.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && listBann != null && listBann.size() > 0) {
            return 1;
        } else {
            return 2;
        }
    }

    class MyView extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView chapter;
        private final TextView superCh;
        private final TextView title;
        private final TextView data;
        private final ImageView collect;

        public MyView(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            chapter = itemView.findViewById(R.id.chapter);
            superCh = itemView.findViewById(R.id.superCh);
            title = itemView.findViewById(R.id.title);
            data = itemView.findViewById(R.id.data);
            collect = itemView.findViewById(R.id.collect);
        }
    }

    class MyBanner extends RecyclerView.ViewHolder {

        private final Banner banner;

        public MyBanner(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    private OnItemUrl onItemUrl;

    public void setOnItemUrl(OnItemUrl onItemUrl) {
        this.onItemUrl = onItemUrl;
    }

    public interface OnItemUrl {
        void onClick(int position);

        void setLike(int position);

        void setDislike(int id);
    }
}
