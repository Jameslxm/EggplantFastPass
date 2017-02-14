package com.james.eggplantfastpass.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.james.eggplantfastpass.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 1 on 2017/2/14.
 */

public class ProblemListAdapter extends RecyclerView.Adapter {
    private List<String> problemList;
    private Context mContext;
    public ProblemListAdapter(List<String> problemList) {
        this.problemList = problemList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view =  inflater.inflate(R.layout.item_problem_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String title = problemList.get(position);
        if(!TextUtils.isEmpty(title)) {
            ((MyViewHolder) holder).tvTitle.setText(title);
        }
    }

    @Override
    public int getItemCount() {
        return problemList != null ? problemList.size() : 0;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.id_tv_title)
        TextView tvTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onRecyclerViewClickListener != null){
                        onRecyclerViewClickListener.onRecyclerViewClick(position);
                    }
                }
            });
        }
    }
    private OnRecyclerViewClickListener onRecyclerViewClickListener;

    public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener onRecyclerViewClickListener) {
        this.onRecyclerViewClickListener = onRecyclerViewClickListener;
    }

    public interface OnRecyclerViewClickListener{
        void onRecyclerViewClick(int position);
    }
}
