package com.james.eggplantfastpass.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        if (viewType == LAYOUT_ONE) {

            View view = inflater.inflate(R.layout.layout_feed_back_one, parent, false);
            return new ViewHolderOne(view);
        } else if (viewType == LAYOUT_TWO) {
            View view = inflater.inflate(R.layout.layout_feed_back_two, parent, false);
            return new ViewHolderTwo(view);
        } else {
            View view = inflater.inflate(R.layout.layout_feed_back_three, parent, false);
            return new ViewHolderThree(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolderThree) {
            int i = position - 2;
            ((ViewHolderThree) holder).tvTitle.setText(hotspotIssues[i]);
        }
    }

    @Override
    public int getItemCount() {
        return hotspotIssues == null ? 2 : hotspotIssues.length + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return LAYOUT_ONE;
        } else if (position == 1) {
            return LAYOUT_TWO;
        } else {
            return LAYOUT_THREE;
        }
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {
        @BindView(R.id.id_ll_transmission_problem)
        LinearLayout idLlTransmissionProblem;
        @BindView(R.id.id_ll_connection_problem)
        LinearLayout idLlConnectionProblem;
        @BindView(R.id.id_ll_storage_problem)
        LinearLayout idLlStorageProblem;
        @BindView(R.id.id_ll_specific_type_problem)
        LinearLayout idLlSpecificTypeProblem;
        public ViewHolderOne(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            idLlTransmissionProblem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onViewHolderOneClickListener != null) {
                        onViewHolderOneClickListener.onViewHolderOneClick(v.getId());
                    }
                }
            });
            idLlConnectionProblem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onViewHolderOneClickListener != null) {
                        onViewHolderOneClickListener.onViewHolderOneClick(v.getId());
                    }
                }
            });
            idLlStorageProblem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onViewHolderOneClickListener != null) {
                        onViewHolderOneClickListener.onViewHolderOneClick(v.getId());
                    }
                }
            });
            idLlSpecificTypeProblem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onViewHolderOneClickListener != null) {
                        onViewHolderOneClickListener.onViewHolderOneClick(v.getId());
                    }
                }
            });
        }
    }

    private class ViewHolderTwo extends RecyclerView.ViewHolder {

        public ViewHolderTwo(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolderThree extends RecyclerView.ViewHolder {
        @BindView(R.id.id_tv_title)
        TextView tvTitle;

        public ViewHolderThree(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (onRecyclerViewClickListener != null) {
                        onRecyclerViewClickListener.onRecyclerViewClick(position);
                    }
                }
            });
        }
    }

    private OnRecyclerViewClickListener onRecyclerViewClickListener;
    private OnViewHolderOneClickListener onViewHolderOneClickListener;

    public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener onRecyclerViewClickListener) {
        this.onRecyclerViewClickListener = onRecyclerViewClickListener;
    }

    public void setOnViewHolderOneClickListener(OnViewHolderOneClickListener onViewHolderOneClickListener) {
        this.onViewHolderOneClickListener = onViewHolderOneClickListener;
    }

    public interface OnRecyclerViewClickListener {
        void onRecyclerViewClick(int position);
    }

    public interface OnViewHolderOneClickListener {
        void onViewHolderOneClick(int id);
    }
}
