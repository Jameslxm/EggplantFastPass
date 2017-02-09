package org.james.material.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by 1 on 2016/9/14.
 * Toast
 */
public class ToastUtil {
    private static Toast toast;

    /**
     * 长时间显示
     * @param context
     * @param content 显示的内容
     */
    public static void showLongToast(Context context,String content){
        if(toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_LONG);
        }else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 短时间显示
     * @param context
     * @param content 显示的内容
     */
    public static void showShortToast(Context context,String content){
        if(toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        }else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 短时间显示
     * @param context
     * @param id
     */
    public static void showShortToast(Context context,int id){
        String content = context.getString(id);
        if(!TextUtils.isEmpty(content)) {
            if (toast == null) {
                toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            } else {
                toast.setText(content);
            }
            toast.show();
        }
    }
}
