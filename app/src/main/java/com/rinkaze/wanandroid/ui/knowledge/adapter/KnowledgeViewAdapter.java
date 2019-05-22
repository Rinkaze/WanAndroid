package com.rinkaze.wanandroid.ui.knowledge.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rinkaze.wanandroid.R;
import com.rinkaze.wanandroid.base.Constants;
import com.rinkaze.wanandroid.bean.KnowledgeHierarchyData;

import java.util.List;

public class KnowledgeViewAdapter extends RecyclerView.Adapter<KnowledgeViewAdapter.ViewHolder> {
    private Context context;
    private List<KnowledgeHierarchyData.DataBean> mKnowledgeList;
    private KnowledgeHierarchyData.DataBean dataBean;

    public KnowledgeViewAdapter(Context context, List<KnowledgeHierarchyData.DataBean> mKnowledgeList) {
        this.context = context;
        this.mKnowledgeList = mKnowledgeList;
    }

    public void setmKnowledgeList(List<KnowledgeHierarchyData.DataBean> mKnowledgeList) {
        this.mKnowledgeList = mKnowledgeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KnowledgeViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_knowledgeview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KnowledgeViewAdapter.ViewHolder viewHolder, final int i) {
        dataBean = mKnowledgeList.get(i);
        viewHolder.tv_data.setText(mKnowledgeList.get(i).getName());

        List<KnowledgeHierarchyData.DataBean.ChildrenBean> children = mKnowledgeList.get(i).getChildren();
         StringBuffer stringBuffer = new StringBuffer();
        for (KnowledgeHierarchyData.DataBean.ChildrenBean child : children) {
            stringBuffer.append(child.getName()).append("  ");
        }
        viewHolder.tv_children.setText(stringBuffer);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onKnowledgeClick!=null){
                    onKnowledgeClick.onKnowledgeItemClick(dataBean,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mKnowledgeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_data;
        private TextView tv_children;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_data=itemView.findViewById(R.id.tv_data);
            tv_children=itemView.findViewById(R.id.tv_children);
        }
    }
    public  interface  onKnowledgeClick{
        void onKnowledgeItemClick(KnowledgeHierarchyData.DataBean dataBean,int postion);
    }
    onKnowledgeClick onKnowledgeClick;

    public void setOnKnowledgeClick(KnowledgeViewAdapter.onKnowledgeClick onKnowledgeClick) {
        this.onKnowledgeClick = onKnowledgeClick;
    }
}
