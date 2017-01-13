package org.james.material;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.james.material.widget.GlideCircleTransform;

/**
 * Created by 1 on 2016/8/9.
 */
public class JBitmap {

    public static void loadImage(Context context,String picUrl,ImageView imageView){
        Glide.with(context).load(picUrl).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(imageView);
    }

    public static void loadLocalImage(Context context,Integer resourceId,ImageView imageView){
        Glide.with(context).load(resourceId).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(imageView);
    }

    public static void loadLocalImage(Context context,String localUrl,ImageView imageView){
        Glide.with(context).load(localUrl).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(imageView);
    }

    public static void loadHeadPortrait(Context context,String picUrl,ImageView imageView){
        Glide.with(context).load(picUrl).transform(new GlideCircleTransform(context)).into(imageView);
    }
}
