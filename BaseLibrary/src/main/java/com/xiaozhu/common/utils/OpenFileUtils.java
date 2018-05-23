package com.xiaozhu.common.utils;

import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.util.Locale;

/**
 * @说明 打开工具类
 * @作者 liuyi
 * @时间 2018/4/12 13:55
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class OpenFileUtils {
    /**
     * 打开文件
     *
     * @param filePath 文件地址
     * @return 文件Intent
     */
    public static Intent openFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists())
            return null;
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase(Locale.getDefault());
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") || end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            return getAudioFileIntent(filePath);
        } else if (end.equals("3gp") || end.equals("mp4")) {
            return getVideoFileIntent(filePath);
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg") || end.equals("bmp")) {
            return getImageFileIntent(filePath);
        } else if (end.equals("apk")) {
            return getApkFileIntent(filePath);
        } else if (end.equals("ppt") || end.equals("pptx")) {
            return getPptFileIntent(filePath);
        } else if (end.equals("xls") || end.equals("xlsx")) {
            return getExcelFileIntent(filePath);
        } else if (end.equals("doc") || end.equals("docx")) {
            return getWordFileIntent(filePath);
        } else if (end.equals("pdf")) {
            return getPdfFileIntent(filePath);
        } else if (end.equals("chm")) {
            return getChmFileIntent(filePath);
        } else if (end.equals("txt")) {
            return getTextFileIntent(filePath, false);
        } else {
            return getAllIntent(filePath);
        }
    }

    /**
     * 获取一个用于打开文件的intent
     *
     * @param fileUrl 文件地址
     * @return 文件的intent
     */
    public static Intent getAllIntent(String fileUrl) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = AppUtils.getUri(new File(fileUrl));
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    /**
     * 获取一个用于打开APK文件的intent
     *
     * @param fileUrl APK文件地址
     * @return APK文件的intent
     */
    public static Intent getApkFileIntent(String fileUrl) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = AppUtils.getUri(new File(fileUrl));
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }

    /**
     * 获取一个用于打开VIDEO文件的intent
     *
     * @param fileUrl 视频地址
     * @return VIDEO文件的intent
     */
    public static Intent getVideoFileIntent(String fileUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = AppUtils.getUri(new File(fileUrl));
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    /**
     * 获取一个用于打开AUDIO文件的intent
     *
     * @param fileUrl 语音地址
     * @return AUDIO文件的intent
     */
    public static Intent getAudioFileIntent(String fileUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = AppUtils.getUri(new File(fileUrl));
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    /**
     * 获取一个用于打开Html文件的intent
     *
     * @param fileUrl html地址
     * @return Html文件的intent
     */
    public static Intent getHtmlFileIntent(String fileUrl) {
        Uri uri = Uri.parse(fileUrl).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(fileUrl).build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    /**
     * 获取一个用于打开图片文件的intent
     *
     * @param fileUrl 图片地址
     * @return 图片文件的intent
     */
    public static Intent getImageFileIntent(String fileUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = AppUtils.getUri(new File(fileUrl));
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    /**
     * 获取一个用于打开PPT文件的intent
     *
     * @param fileUrl PPT文件地址
     * @return PPT文件的intent
     */
    public static Intent getPptFileIntent(String fileUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = AppUtils.getUri(new File(fileUrl));
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    /**
     * 获取一个用于打开Excel文件的intent
     *
     * @param fileUrl 文件地址
     * @return Excel文件的intent
     */
    public static Intent getExcelFileIntent(String fileUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = AppUtils.getUri(new File(fileUrl));
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    /**
     * 获取一个用于打开Word文件的intent
     *
     * @param fileUrl 文件地址
     * @return Word文件的intent
     */
    public static Intent getWordFileIntent(String fileUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = AppUtils.getUri(new File(fileUrl));
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    /**
     * 获取一个用于打开CHM文件的intent
     *
     * @param fileUrl 文件地址
     * @return CHM文件的intent
     */
    public static Intent getChmFileIntent(String fileUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = AppUtils.getUri(new File(fileUrl));
        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }

    /**
     * 获取一个用于打开文本文件的intent
     *
     * @param fileUrl 文件地址
     * @return 文本文件的intent
     */
    public static Intent getTextFileIntent(String fileUrl, boolean paramBoolean) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (paramBoolean) {
            Uri uri1 = Uri.parse(fileUrl);
            intent.setDataAndType(uri1, "text/plain");
        } else {
            Uri uri2 = AppUtils.getUri(new File(fileUrl));
            intent.setDataAndType(uri2, "text/plain");
        }
        return intent;
    }

    /**
     * 获取一个用于打开PDF文件的intent
     *
     * @param fileUrl 文件地址
     * @return PDF文件的intent
     */
    public static Intent getPdfFileIntent(String fileUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = AppUtils.getUri(new File(fileUrl));
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }

}
