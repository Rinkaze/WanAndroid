package com.rinkaze.wanandroid.ui.main.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.bean.official.SearchBean;
import com.rinkaze.wanandroid.widget.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SearchBean.DataBean.DatasBean> list;
    Context context;
    private List<Integer> colorlist;

    public SearchAdapter(List<SearchBean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_search, null);
        return new Myview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Myview myview= (Myview) viewHolder;
        colorlist = new ArrayList<>();
        colorlist.add(Color.RED);
        colorlist.add(Color.BLUE);
        colorlist.add(Color.GRAY);
        colorlist.add(R.color.white);
        colorlist.add(R.color.c_e98f36);
        colorlist.add(R.color.colorAccent);
        for (int j = 0; j < 15; j++) {
            View view = View.inflate(context,R.layout.view_text,null);
            TextView tv = view.findViewById(R.id.textview);
            tv.setText(list.get(i).getTitle());
            tv.setTextColor(colorlist.get(i%colorlist.size()));
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            myview.search.addView(view);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class Myview extends RecyclerView.ViewHolder {

        private final FlowLayout search;
        public Myview(@NonNull View itemView) {
            super(itemView);
            search = itemView.findViewById(R.id.search_flow);
        }
    }
}
