package com.james.eggplantfastpass.adapter;

import android.support.v7.widget.RecyclerView;

import com.james.eggplantfastpass.bean.VideoInfoBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * Adapter holding a list of animal names of type String. Note that each item must be unique.
 */
public abstract class AnimalsAdapter<VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {
  private ArrayList<String> items = new ArrayList<String>();
  private List<VideoInfoBean> videoInfoBeanArrayList = new ArrayList<>();
  public AnimalsAdapter() {
    setHasStableIds(true);
  }

  public void add(String object) {
    items.add(object);
    notifyDataSetChanged();
  }

  public void add(int index, String object) {
    items.add(index, object);
    notifyDataSetChanged();
  }

  public void addAll(Collection<? extends String> collection) {
    if (collection != null) {
      items.addAll(collection);
      notifyDataSetChanged();
    }
  }

  public void addAll(String... items) {
    addAll(Arrays.asList(items));
  }

  public void addAll(List<VideoInfoBean> videoInfoBeanList){
    if(videoInfoBeanList != null && videoInfoBeanList.size() > 0) {
      this.videoInfoBeanArrayList.addAll(videoInfoBeanList);
      notifyDataSetChanged();
    }
  }
  public void clear() {
    items.clear();
    notifyDataSetChanged();
  }

  public void remove(String object) {
    items.remove(object);
    notifyDataSetChanged();
  }

  public String getItem(int position) {
    return items.get(position);
  }

  public VideoInfoBean getListVideoInfoBean(int position){

    return this.videoInfoBeanArrayList.get(position);
  }
  @Override
  public long getItemId(int position) {
    return videoInfoBeanArrayList.get(position).getFolder().hashCode();
  }

  @Override
  public int getItemCount() {
    return videoInfoBeanArrayList.size();
  }
}
