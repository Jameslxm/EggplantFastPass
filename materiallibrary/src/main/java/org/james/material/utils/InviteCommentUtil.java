package org.james.material.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;

import org.james.material.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.james.material.utils.PreferenceHelper.readString;

/**
 * 请发表评论
 * Created by 1 on 2016/11/9.
 */
public class InviteCommentUtil {
    private static InviteCommentUtil inviteCommentUtil = null;
    private String mCurrentTimeStr;
    private String savecommenttime = "saveCommentTime";
    public static InviteCommentUtil getInstance(){
        if(inviteCommentUtil == null){
            synchronized (InviteCommentUtil.class){
                if(inviteCommentUtil == null){
                    inviteCommentUtil = new InviteCommentUtil();
                }
            }
        }
        return inviteCommentUtil;
    }
    /**
     * 选择哪天弹邀请评论框
     * 当前的时间和保存的时间比较，如果满足一周，弹出邀请。无，不弹出。
     */
    public void startComment(Activity activity,boolean isClick) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        mCurrentTimeStr = df.format(new Date());
        String saveCommentTime = readString(activity, Constants.INVITE_COMMENT_FILE_NAME, savecommenttime, mCurrentTimeStr);//获取保存发表评论的时间
        int time = (int) TimeUtils.getIntervalTime(mCurrentTimeStr, saveCommentTime, TimeUtils.UNIT_DAY);
        MyLog.d("time:"+time+";mCurrentTimeStr:"+mCurrentTimeStr+";saveCommentTime:"+saveCommentTime);
        if(time == 0){
            setComment(activity,mCurrentTimeStr);
        }
        if(isClick){
            showDialog(activity);
        }else {
            if (time >= 7) {
                showDialog(activity);
            }
        }
    }

    private void showDialog(final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(Html.fromHtml("您已经累计阅读<font color=#FF0000>" + 1000+"+" + "</font>字，再接再厉哦！如果喜欢我，希望您能在应用市场给予<font color=#FF0000>五星</font>好评！"));
        builder.setTitle("求好评");
        builder.setPositiveButton("好评鼓励",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setComment(activity,mCurrentTimeStr);
                        try {
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.setData(Uri.parse("market://details?id=" + activity.getPackageName()));
                            activity.startActivity(intent);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("下次再说",
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        String time = PreferenceHelper.readString(activity,Constants.INVITE_COMMENT_FILE_NAME,savecommenttime);
                        if(TextUtils.isEmpty(time)) {
                            setComment(activity, mCurrentTimeStr);
                        }
                    }
                });
        builder.create().show();
    }
    /**
     * 保存，直到下周再提示
     */
    private void setComment(Activity activity,String mCurrentTimeStr) {
        PreferenceHelper.write(activity,Constants.INVITE_COMMENT_FILE_NAME,savecommenttime,mCurrentTimeStr);
   }
}
