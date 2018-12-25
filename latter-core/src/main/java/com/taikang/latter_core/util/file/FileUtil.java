package com.taikang.latter_core.util.file;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import com.taikang.latter_core.app.Latte;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Time：2018/12/12
 * Author: gaonz
 * Description:
 */
public class FileUtil {

    //格式化模板
    public static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";
    public static final String SDCARD_DIR =
            Environment.getExternalStorageDirectory().getPath();

    //默认本地上传图片目录
    public static final String UPLOAD_PHOT_DIR =
            Environment.getExternalStorageDirectory().getPath() + "/a_upload_photos/";

    //网页缓存地址
    public static final String WEB_CACHE_DIR =
            Environment.getExternalStorageDirectory().getPath() + "/app_web_cache/";

    //系统相机目录
    public static final String CAMERA_PHOTO_DIR =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath();

    public static String getTimeFormatName(String timeFormatHeader) {
        Date date = new Date(System.currentTimeMillis());
        //必须要加上单引号
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'" + timeFormatHeader + "'" + TIME_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static File writeToDisk(InputStream is, String dir, String prefix, String extensin) {
        File file = FileUtil.createFileByTime(dir, prefix, extensin);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] data = new byte[1024 * 4];

            int count;
            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
            }

            bos.flush();
            fos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static File writeToDisk(InputStream is, String dir, String name) {
        File file = FileUtil.createFile(dir, name);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] data = new byte[1024 * 4];

            int count;
            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
            }

            bos.flush();
            fos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    //获取文件的MIME
    public static String getMimeType(String filePath) {
        String extension = getExtension(filePath);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    //获取文件的后缀名
    public static String getExtension(String filePath) {
        String suffix = "";
        File file = new File(filePath);
        String name = file.getName();
        final int idx = name.lastIndexOf(".");
        if (idx > 0) {
            suffix = name.substring(idx + 1);
        }
        return suffix;
    }

    /**
     * @param timeFormatHeader 格式化的头（除去时间部分）
     * @param extension 后缀名
     * @return 返回时间格式化后的文件名
     */
    public static String getFileNameByTime(String timeFormatHeader, String extension) {
        return getTimeFormatName(timeFormatHeader) + "." + extension;
    }


    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File createDir(String sdcardDirName) {
        //拼接成sd卡中完整的dir
        String dir = SDCARD_DIR + File.separator + sdcardDirName + File.separator;
        File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return fileDir;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File createFile(String sdcardName, String fileName) {
        return new File(createDir(sdcardName), fileName);
    }

    public static File createFileByTime(String sdcardDirName, String timeFormatHeader, String extensin) {
        String fileName = getFileNameByTime(timeFormatHeader, extensin);
        return createFile(sdcardDirName, fileName);
    }

    public static String getRawFile(int rawId) {
        InputStream is = Latte.getApplicationContext().getResources().openRawResource(rawId);
        BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder stringBuilder = new StringBuilder();
        String str;
        try {
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                bis.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return stringBuilder.toString();
    }
}
