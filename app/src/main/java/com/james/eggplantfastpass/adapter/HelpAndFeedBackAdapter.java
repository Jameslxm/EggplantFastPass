package com.james.eggplantfastpass.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.james.eggplantfastpass.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 1 on 2017/2/13.
 */

public class HelpAndFeedBackAdapter extends RecyclerView.Adapter {
    private static final int LAYOUT_ONE = 1;
    private static final int LAYOUT_TWO = 2;
    private static final int LAYOUT_THREE = 3;
    private Context mContext;
    private String[] hotspotIssues;
    public HelpAndFeedBackAdapter(String[] hotspotIssues) {
        this.hotspotIssues = hotspotIssues;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if(viewType == LAYOUT_ONE){

            View view = inflater.inflate(R.layout.layout_feed_back_one,parent,false);
            return new ViewHolderOne(view);
        }else if(viewType == LAYOUT_TWO){
            View view = inflater.inflate(R.layout.layout_feed_back_two,parent,false);
            return new ViewHolderTwo(view);
        }else {
            View view = inflater.inflate(R.layout.layout_feed_back_three,parent,false);
            return new ViewHolderThree(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ViewHolderThree){
            int i = position - 2;
            ((ViewHolderThree) holder).tvTitle.setText(hotspotIssues[i]);
        }
    }

    @Override
    public int getItemCount() {
        return hotspotIssues == null ? 2 :hotspotIssues.length + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return LAYOUT_ONE;
        }else if(position == 1){
            return LAYOUT_TWO;
        }else {
            return LAYOUT_THREE;
        }
    }
    private class ViewHolderOne extends RecyclerView.ViewHolder{

        public ViewHolderOne(View itemView) {
            super(itemView);
        }
    }

    private class ViewHolderTwo extends RecyclerView.ViewHolder{

        public ViewHolderTwo(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolderThree extends RecyclerView.ViewHolder{
        @BindView(R.id.id_tv_title)
        TextView tvTitle;
        public ViewHolderThree(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
