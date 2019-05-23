package com.rinkaze.wanandroid.ui.navigation.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.ui.navigation.bean.Navi_Tab_Bean;
import com.rinkaze.wanandroid.widget.FlowLayout;

import java.util.ArrayList;
import java.util.Random;

public class NaviRecAdapter extends RecyclerView.Adapter<NaviRecAdapter.ViewHolder> {
    private ArrayList<Navi_Tab_Bean.DataEntity> list=new ArrayList<>();
    private Context context;
    private TextView text;
    private ArrayList<Integer> colorlist;
    private OnItenClickListener listener;

    public void setListener(OnItenClickListener listener) {
        this.listener = listener;
    }

    public NaviRecAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<Navi_Tab_Bean.DataEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.item_navi_rec,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        colorlist = new ArrayList<>();
        colorlist.add(Color.RED);
        colorlist.add(Color.BLUE);
        colorlist.add(Color.GRAY);
        colorlist.add(R.color.white);
        colorlist.add(R.color.c_e98f36);
        colorlist.add(R.color.colorAccent);
        holder.title.setText(list.get(position).getName());
            holder.flow.removeAllViews();
        for (int i = 0; i < list.get(position).getArticles().size(); i++) {
            View view = View.inflate(context,R.layout.view_text,null);
            TextView tv = view.findViewById(R.id.textview);
            tv.setText(list.get(position).getArticles().get(i).getTitle());
            tv.setTextColor(colorlist.get(i%colorlist.size()));
            holder.flow.addView(view);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.listener(list.get(position).getArticles().get(position).getLink(),list.get(position).getArticles().get(position).getTitle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private FlowLayout flow;
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.navi_title);
            flow = itemView.findViewById(R.id.flow);
        }
    }

    public interface OnItenClickListener{
        void listener(String link,String title);
    }
}
