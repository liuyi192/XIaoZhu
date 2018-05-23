package com.xiaozhu.common.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * @说明 文件管理类
 * @作者 liuyi
 * @时间 2018/4/26 11:44
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class FileManagerUtils {
    public static final String IMG_FOLDER = "/img/";//图片地址
    public static final String LOG_FOLDER = "/log/";//日志地址
    public static final String AUDIO_FOLDER = "/audio/";//音频
    public static final String APK_FOLDER = "/apk/";// APK文件
    public static final String TXT_FOLDER = "/txt/";//文件
    public static final String COMPRESS_FOLDER = "/compress/";// 压缩图片
    public static final String DOWNLOAD_FOLDER = "/download/";//下載路徑

    private String appPath = "";
    private static FileManagerUtils instance;

    public static FileManagerUtils getInstance() {
        if (instance == null) {
            synchronized (FileManagerUtils.class) {
                if (instance == null) {
                    instance = new FileManagerUtils();
                }
            }
        }
        return instance;
    }

    public void setFolderName(String name) {
        if (isSdcard()) {
            String path = getSdcardPath() + name;
            File dirFile = new File(path);
            if (!dirFile.exists() && !dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
            appPath = dirFile.getAbsolutePath();
        }
    }

    public boolean isSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public String getSdcardPath() {
        if (isSdcard()) {
            return Environment.getExternalStorageDirectory().getPath() + "/";
        } else {
            return "";
        }
    }

    public String getAppPath() {
        return TextUtils.isEmpty(appPath) ? "/" : appPath;
    }

    public long getFileSize(String filePath) {
        if (TextUtils.isEmpty(filePath)) return 0;
        long size = 0;
        File flist[] = (new File(filePath)).listFiles();
        if (flist == null && flist.length <= 0) return 0;
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSize(flist[i].getAbsolutePath());
            } else {
                size = size + flist[i].length();
            }
        }
        return size;
    }

    public String getFileSize() {
        String str = "0";
        long size = getFileSize(getAppPath());
        if (size == 0) {
            str = "0 K";
        } else if (size > 0 && size < 1024) {
            str = size + " K";
        } else if (size > 1024 && size < 1024 * 1024) {
            str = size / 1024 + " K";
        } else if (size >= 1024 * 1024) {
            str = String.valueOf(size / (1024 * 1024)) + " M";
        }
        return str;
    }

    public void delFolder(String folderPath) {
        if (TextUtils.isEmpty(folderPath)) return;
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            File myFilePath = new File(folderPath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param filePath
     */
    public void delFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) return;
        if (!(new File(filePath)).exists()) return;
        try {
            File myFilePath = new File(filePath);
            myFilePath.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean delAllFile(String path) {
        if (TextUtils.isEmpty(path)) return false;
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    public String getCompressFolder() {
        String path = null;
        if (!TextUtils.isEmpty(getAppPath())) {
            path = getAppPath() + COMPRESS_FOLDER;
            File dirFile = new File(path);
            if (!dirFile.exists() && !dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
        }
        return TextUtils.isEmpty(path) ? "" : path;
    }

    public String getApkFolder() {
        String path = null;
        if (!TextUtils.isEmpty(getAppPath())) {
            path = getAppPath() + APK_FOLDER;
            File dirFile = new File(path);
            if (!dirFile.exists() && !dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
        }
        return TextUtils.isEmpty(path) ? "" : path;
    }

    public String getLogFolder() {
        String path = null;
        if (!TextUtils.isEmpty(getAppPath())) {
            path = getAppPath() + LOG_FOLDER;
            File dirFile = new File(path);
            if (!dirFile.exists() && !dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
        }
        return TextUtils.isEmpty(path) ? "" : path;
    }

    public String getImgFolder() {
        String path = null;
        if (!TextUtils.isEmpty(getAppPath())) {
            path = getAppPath() + IMG_FOLDER;
            File dirFile = new File(path);
            if (!dirFile.exists() && !dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
        }
        return TextUtils.isEmpty(path) ? "" : path;
    }

    public String getDownloadFolder() {
        String path = null;
        if (!TextUtils.isEmpty(getAppPath())) {
            path = getAppPath() + DOWNLOAD_FOLDER;
            File dirFile = new File(path);
            if (!dirFile.exists() && !dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
        }
        return TextUtils.isEmpty(path) ? "" : path;
    }

    public String getAudioFolder() {
        String path = null;
        if (!TextUtils.isEmpty(getAppPath())) {
            path = getAppPath() + AUDIO_FOLDER;
            File dirFile = new File(path);
            if (!dirFile.exists() && !dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
        }
        return TextUtils.isEmpty(path) ? "" : path;
    }

    public String getTxtFolder() {
        String path = null;
        if (!TextUtils.isEmpty(getAppPath())) {
            path = getAppPath() + TXT_FOLDER;
            File dirFile = new File(path);
            if (!dirFile.exists() && !dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
        }
        return TextUtils.isEmpty(path) ? "" : path;
    }


    /**
     * 根据文件路径获取文件名称
     *
     * @param path 文件路径
     * @return 文件名称
     */
    public String getFileName(String path) {
        String str = "";
        String paths[] = path.split("/");
        if (paths.length > 1) {
            str = paths[paths.length - 1];
        }
        return str;
    }

    /**
     * 获取真实的路径
     *
     * @param context 上下文
     * @param uri     uri
     * @return 文件路径
     */
    public String getRealPathFromURI(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            return uri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            String realPath = cursor.getString(index);
            cursor.close();
            return realPath;
        }
    }

    /**
     * 截取文件名称
     *
     * @param fileName 文件名称
     */
    public String[] splitFileName(String fileName) {
        String name = fileName;
        String extension = "";
        int i = fileName.lastIndexOf(".");
        if (i != -1) {
            name = fileName.substring(0, i);
            extension = fileName.substring(i);
        }
        return new String[]{name, extension};
    }

    /**
     * 获取文件名称
     *
     * @param context 上下文
     * @param uri     uri
     * @return 文件名称
     */
    public String getFileName(Context context, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf(File.separator);
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    /**
     * 文件大小格式化
     *
     * @param fileS
     * @return
     */
    public String FormatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 后缀
     *
     * @param fileName
     * @param fileSuffix
     * @return
     */
    public boolean checkSuffix(String fileName, String[] fileSuffix) {
        for (String suffix : fileSuffix) {
            if (fileName != null) {
                if (fileName.toLowerCase().endsWith(suffix)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取文件路径
     *
     * @param type
     * @return
     */
    public String getFolderPath(int type) {
        String folder = FileManagerUtils.getInstance().getAppPath() + "/";
        if (FileType.APK.ordinal() == type) {
            folder = FileManagerUtils.getInstance().getApkFolder();
        } else if (FileType.IMG.ordinal() == type) {
            folder = FileManagerUtils.getInstance().getImgFolder();
        } else if (FileType.AUDIO.ordinal() == type) {
            folder = FileManagerUtils.getInstance().getAudioFolder();
        } else if (FileType.TXT.ordinal() == type) {
            folder = FileManagerUtils.getInstance().getTxtFolder();
        }
        return folder;
    }
}
