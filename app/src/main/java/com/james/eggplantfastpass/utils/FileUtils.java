package com.james.eggplantfastpass.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.bean.FileInfo;
import com.james.eggplantfastpass.bean.PicInfo;
import com.james.eggplantfastpass.bean.VideoInfoBean;

import org.james.material.utils.MyLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/1/23 0023.
 */

public class FileUtils {
    /**
     * 自定义文件类型
     */
    public static final int TYPE_APK = 1;
    public static final int TYPE_JPEG = 2;
    public static final int TYPE_MP3 = 3;
    public static final int TYPE_MP4 = 4;

    /**
     * 小数的格式化
     */
    public static final DecimalFormat FORMAT = new DecimalFormat("####.##");

    public static List<FileInfo> getSpecificTypeFiles(Context context, String[] extension) {
        List<FileInfo> fileInfoList = new ArrayList<>();
        Uri fileUri = MediaStore.Files.getContentUri("external");
        String[] projection = new String[]{MediaStore.Files.FileColumns.DATA, MediaStore.Files.FileColumns.TITLE};
        //构造筛选条件语句
        String selection = "";
        for (int i = 0; i < extension.length; i++) {
            if (i != 0) {
                selection = selection + " OR ";
            }
            selection = selection + MediaStore.Files.FileColumns.DATA + " LIKE '%" + extension[i] + "'";
        }
        String sortOrder = MediaStore.Files.FileColumns.DATE_MODIFIED;
        //获取特定的文件
        Cursor cursor = context.getContentResolver().query(fileUri, projection, selection, null, sortOrder);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String data = cursor.getString(0);
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFilePath(data);
                //文件大小
                long size = 0;
                File file = new File(data);
                size = file.length();
                fileInfo.setFileSize(size);
                fileInfoList.add(fileInfo);
            }
        }
        return fileInfoList;
    }

    public static List<FileInfo> getDetailFileInfos(Context context, List<FileInfo> fileInfoList, int type) {
        if (fileInfoList == null || fileInfoList.size() <= 0) {
            return fileInfoList;
        }
        for (FileInfo fileInfo : fileInfoList) {
            if (fileInfo != null) {
                //获取文件名
                fileInfo.setFileName(getFileName(fileInfo.getFilePath()));
                //设置文件大小
                fileInfo.setSizeDesc(getFileSize(fileInfo.getFileSize()));
                if (type == TYPE_APK) {
                    fileInfo.setBitmap(FileUtils.drawableToBitmap(FileUtils.getApkThumbnail(context, fileInfo.getFilePath())));
                } else if (type == FileInfo.TYPE_MP4) {
                    fileInfo.setBitmap(FileUtils.getScreenshotBitmap(context, fileInfo.getFilePath(), FileInfo.TYPE_MP4));
                } else if (type == FileInfo.TYPE_MP3) { //mp3不需要缩略图

                } else if (type == FileInfo.TYPE_JPG) {//由Glide图片加载框架加载

                }
            }
        }
        return fileInfoList;
    }

    /**
     * 获取缩略图的Bitmap
     *
     * @param filePath
     * @param type
     * @return
     */
    public static Bitmap getScreenshotBitmap(Context context, String filePath, int type){
        Bitmap bitmap = null;
        switch (type){
            case TYPE_APK:{
                Drawable drawable = getApkThumbnail(context, filePath);
                if(drawable != null){
                    bitmap = drawableToBitmap(drawable);
                }else {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
                }
                bitmap = ScreenshotUtils.extractThumbnail(bitmap, 100, 100);
                break;
            }
            case TYPE_JPEG:{
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(filePath)));
                } catch (FileNotFoundException e) {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
                }
                bitmap = ScreenshotUtils.extractThumbnail(bitmap, 100, 100);
                break;
            }
            case TYPE_MP3:{

                break;
            }
            case TYPE_MP4:{
                try {
                    bitmap = ScreenshotUtils.createVideoThumbnail(filePath);
                } catch (Exception e) {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_mp4);
                }
                bitmap = ScreenshotUtils.extractThumbnail(bitmap, 100, 100);
                break;
            }
        }
        return bitmap;
    }
    public static Drawable getApkThumbnail(Context context, String apk_path) {
        if (context == null) {
            return null;
        }
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = pm.getPackageArchiveInfo(apk_path, PackageManager.GET_ACTIVITIES);
        if(packageInfo != null) {
            ApplicationInfo appInfo = packageInfo.applicationInfo;
            /**获取apk的图标 */
            appInfo.sourceDir = apk_path;
            appInfo.publicSourceDir = apk_path;
            if (appInfo != null) {
                Drawable app_icon = appInfo.loadIcon(pm);
                return app_icon;
            }
        }
        return null;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;

    }

    /**
     * 根据传入的byte数量转换为对应的byte, Kbyte, Mbyte, Gbyte单位的字符串
     *
     * @param size
     * @return
     */
    private static String getFileSize(long size) {
        double value;
        if (size < 0) {
            return "0B";
        }
        if ((size / 1024) < 1) {
            return size + "B";
        } else if ((size / (1024 * 1024)) < 1) {
            value = size / 1024f;
            return FORMAT.format(value) + "KB";
        } else if ((size / (1024 * 1024 * 1024)) < 1) {
            value = (size * 100 / (1024 * 1024)) / 100f;
            return FORMAT.format(value) + "MB";
        } else {
            value = (size * 1001 / (10241 * 10241 * 10241)) / 100f;
            return FORMAT.format(value) + "GB";
        }
    }

    /**
     * 获取文件名
     *
     * @param filePath
     */
    private static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return "";
        }
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }

    public static List<FileInfo> scanInstallApp(Context context){
        List<FileInfo> fileInfoList = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        for(PackageInfo packageInfo : packageInfoList){
            FileInfo fileInfo = new FileInfo();
            String appName = (String) packageInfo.applicationInfo.loadLabel(packageManager);//应用名
            Drawable appIcon = packageInfo.applicationInfo.loadIcon(packageManager);//icon
            //过滤掉系统app
            if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0) {
                continue;
            }
            if(!TextUtils.isEmpty(appName)) {
                fileInfo.setFileName(appName);
            }
            Bitmap iconBitmap = FileUtils.drawableToBitmap(appIcon);
            if(iconBitmap != null){
                fileInfo.setBitmap(iconBitmap);
            }
            fileInfoList.add(fileInfo);
        }
        return fileInfoList;
    }


    //照相机图片
    public static List<PicInfo> getCameraImage(Context context){
        List<PicInfo> picInfoList = new ArrayList<>();
        String[] projection = new String[]{MediaStore.Images.ImageColumns._ID,MediaStore.Images.ImageColumns.DATA,MediaStore.Images.ImageColumns.DATE_MODIFIED};
        String selection = MediaStore.Images.ImageColumns._ID ;
        String orderSort = MediaStore.Images.ImageColumns.DATE_MODIFIED;
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,projection,selection,null,orderSort);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String data = cursor.getString(1);
                String date = cursor.getString(2);
                MyLog.d("id:"+id+";date:"+date);
                PicInfo picInfo = new PicInfo();
                picInfoList.add(picInfo);
            }
        }
        return picInfoList;
    }


    //获取视频信息
    public static List<VideoInfoBean> getVideoInfo(Context context){
        MyLog.d("title:--------");
        List<VideoInfoBean> videoInfoBeanList = new ArrayList<>();
        //查找数据
        String[] projection = new String[]{MediaStore.Video.Media.TITLE,MediaStore.Video.Media.DATA};
        String orderSort = MediaStore.Video.Media.DEFAULT_SORT_ORDER;
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,projection,null,null,orderSort);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(0);
                String data = cursor.getString(1);
                String folder = null;
                if(!TextUtils.isEmpty(data)){
                    int lastBackslashIndex = data.lastIndexOf("/");
                    if(lastBackslashIndex > 0) {
                        String temp = data.substring(0, lastBackslashIndex);
                        int tempBackslashIndex = temp.lastIndexOf("/");
                        folder = temp.substring(tempBackslashIndex+1);
                        MyLog.d("folder:" + folder);
                    }
                    VideoInfoBean videoInfoBean = new VideoInfoBean();
                    videoInfoBean.setTitle(title);
                    videoInfoBean.setData(data);
                    videoInfoBean.setFolder(folder);
                    videoInfoBeanList.add(videoInfoBean);
                    MyLog.d("title:"+title+";data:"+data);
                }

            }
        }
        //排序
        FolderComparator folderComparator = new FolderComparator();
        Collections.sort(videoInfoBeanList,folderComparator);
        return videoInfoBeanList;
    }
}
