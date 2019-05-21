package com.rinkaze.wanandroid.ui.navigation.adapter;

import android.content.Context;
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

public class NaviRecAdapter extends RecyclerView.Adapter<NaviRecAdapter.ViewHolder> {
    private ArrayList<Navi_Tab_Bean.DataEntity> list=new ArrayList<>();
    private Context context;
    private TextView text;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getName());

        for (int i = 0; i < list.get(position).getArticles().size(); i++) {
            TextView tv = (TextView) View.inflate(context,R.layout.view_text,null);
            tv.setText(list.get(position).getArticles().get(i).getTitle());
            holder.flow.addView(tv);
        }



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
}
