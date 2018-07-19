package com.app.liliuhuan.normallibrary.utils.crash;


import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;


import com.app.liliuhuan.normallibrary.utils.common.CommonUtil;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lc on 2014/6/7.
 */
public class FileUtil {

    public static final String ROOT_DIR = "mycacje";
    public static final String DOWNLOAD_DIR = "download";
    public static final String DB_DIR = "db";
    public static final String CACHE_DIR = "cache";
    public static final String LOG_DIR = "fsk_log";
    public static final String IMAGE_DIR = "image";
    public static final String VIDEO_DIR = "video";
    public static final String WORD = "word";

    /**
     * 判断SD卡是否挂载
     */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取下载目录
     */
    public static String getDownloadDir() {
        return getDir(DOWNLOAD_DIR);
    }

    /**
     * 获取文档下载目录
     */
    public static String getDownloadWord() {
        return getDir(WORD);
    }

    public static String getDBDir() {
        return getDir(DB_DIR);
    }

    /**
     * 获取缓存目录
     */
    public static String getCacheDir() {
        return getDir(CACHE_DIR);
    }

    /**
     * 获取icon目录
     */
    @Deprecated
    public static String getIconDir() {
        return getDir(IMAGE_DIR);
    }

    /**
     * 获取image目录
     */
    public static String getImageDir() {
        return getDir(IMAGE_DIR);
    }

    /**
     * 获取video目录
     */
    public static String getVideoDir() {
        return getDir(VIDEO_DIR);
    }

    /**
     * 获取日志目录
     */
    public static String getLogDir() {
        return getDir(LOG_DIR);
    }

    /**
     * 获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录
     */
    public static String getDir(String name) {
        StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getExternalStoragePath());
        } else {
            sb.append(getCachePath());
        }
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        if (createDirs(path)) {
            return path;
        } else {
            return null;
        }
    }

    /**
     * 获取SD下的应用目录
     */
    public static String getExternalStoragePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(ROOT_DIR);
        sb.append(File.separator);
        return sb.toString();
    }

    /***
     * 绝对路径获取缓存文件
     *
     * @param url
     * @return
     */
    public static File getCacheFile(String url) {
        File cacheFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/009/"
                + getFileName(url));
        return cacheFile;
    }

    public static File getCacheDir(String url) {
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/009/");
    }

    /**
     * 获取应用的cache目录
     */
    public static String getCachePath() {
        File f = CommonUtil.getAppContext().getCacheDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + "/";
        }
    }

    /**
     * 创建文件夹
     */
    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 复制文件，可以选择是否删除源文件
     */
    public static boolean copyFile(String srcPath, String destPath, boolean deleteSrc) {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        return copyFile(srcFile, destFile, deleteSrc);
    }

    /**
     * 复制文件，可以选择是否删除源文件
     */
    public static boolean copyFile(File srcFile, File destFile, boolean deleteSrc) {
        if (!srcFile.exists() || !srcFile.isFile()) {
            return false;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = in.read(buffer)) > 0) {
                out.write(buffer, 0, i);
                out.flush();
            }
            if (deleteSrc) {
                srcFile.delete();
            }
        } catch (Exception e) {
           // CommonLog.e(e);
            return false;
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 判断文件是否可写
     */
    public static boolean isWriteable(String path) {
        try {
            if (TextUtils.isEmpty(path)) {
                return false;
            }
            File f = new File(path);
            return f.exists() && f.canWrite();
        } catch (Exception e) {
            //CommonLog.e(e);
            return false;
        }
    }

    /**
     * 修改文件的权限,例如"777"等
     */
    public static void chmod(String path, String mode) {
        try {
            String command = "chmod " + mode + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (Exception e) {
            //CommonLog.e(e);
        }
    }

    /**
     * 把数据写入文件
     *
     * @param is       数据流
     * @param path     文件路径
     * @param recreate 如果文件存在，是否需要删除重建
     * @return 是否写入成功
     */
    public static boolean writeFile(InputStream is, String path, boolean recreate) {
        boolean res = false;
        File f = new File(path);
        FileOutputStream fos = null;
        try {
            if (recreate && f.exists()) {
                f.delete();
            }
            if (!f.exists() && null != is) {
                File parentFile = new File(f.getParent());
                parentFile.mkdirs();
                int count = -1;
                byte[] buffer = new byte[1024];
                fos = new FileOutputStream(f);
                while ((count = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, count);
                }
                res = true;
            }
        } catch (Exception e) {
          //  CommonLog.e(e);
        } finally {
            close(fos);
            close(is);
        }
        return res;
    }

    /**
     * 把字符串数据写入文件
     *
     * @param content 需要写入的字符串
     * @param path    文件路径名称
     * @param append  是否以添加的模式写入
     * @return 是否写入成功
     */
    public static boolean writeFile(byte[] content, String path, boolean append) {
        boolean res = false;
        File f = new File(path);
        RandomAccessFile raf = null;
        try {
            if (f.exists()) {
                if (!append) {
                    f.delete();
                    f.createNewFile();
                }
            } else {
                f.createNewFile();
            }
            if (f.canWrite()) {
                raf = new RandomAccessFile(f, "rw");
                raf.seek(raf.length());
                raf.write(content);
                res = true;
            }
        } catch (Exception e) {
           // CommonLog.e(e);
        } finally {
            close(raf);
        }
        return res;
    }

    /**
     * 把字符串数据写入文件
     *
     * @param content 需要写入的字符串
     * @param path    文件路径名称
     * @param append  是否以添加的模式写入
     * @return 是否写入成功
     */
    public static boolean writeFile(String content, String path, boolean append) {
        return writeFile(content.getBytes(), path, append);
    }

    /**
     * 把键值对写入文件
     *
     * @param filePath 文件路径
     * @param key      键
     * @param value    值
     * @param comment  该键值对的注释
     */
    public static void writeProperties(String filePath, String key, String value, String comment) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);// 先读取文件，再把键值对追加到后面
            p.setProperty(key, value);
            fos = new FileOutputStream(f);
            p.store(fos, comment);
        } catch (Exception e) {
           // CommonLog.e(e);
        } finally {
            close(fis);
            close(fos);
        }
    }

    /**
     * 根据值读取
     */
    public static String readProperties(String filePath, String key, String defaultValue) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(filePath)) {
            return null;
        }
        String value = null;
        FileInputStream fis = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);
            value = p.getProperty(key, defaultValue);
        } catch (IOException e) {
            //CommonLog.e(e);
        } finally {
            close(fis);
        }
        return value;
    }

    /**
     * 把字符串键值对的map写入文件
     */
    public static void writeMap(String filePath, Map<String, String> map, boolean append, String comment) {
        if (map == null || map.size() == 0 || TextUtils.isEmpty(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            Properties p = new Properties();
            if (append) {
                fis = new FileInputStream(f);
                p.load(fis);// 先读取文件，再把键值对追加到后面
            }
            p.putAll(map);
            fos = new FileOutputStream(f);
            p.store(fos, comment);
        } catch (Exception e) {
          //  CommonLog.e(e);
        } finally {
            close(fis);
            close(fos);
        }
    }

    /**
     * 把字符串键值对的文件读入map
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map<String, String> readMap(String filePath, String defaultValue) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        Map<String, String> map = null;
        FileInputStream fis = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);
            map = new HashMap<String, String>((Map) p);// 因为properties继承了map，所以直接通过p来构造一个map
        } catch (Exception e) {
           // CommonLog.e(e);
        } finally {
            close(fis);
        }
        return map;
    }

    /**
     * 改名
     */
    public static boolean copy(String src, String des, boolean delete) {
        File file = new File(src);
        if (!file.exists()) {
            return false;
        }
        File desFile = new File(des);
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new FileOutputStream(desFile);
            byte[] buffer = new byte[1024];
            int count = -1;
            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
                out.flush();
            }
        } catch (Exception e) {
         //   CommonLog.e(e);
            return false;
        } finally {
            close(in);
            close(out);
        }
        if (delete) {
            file.delete();
        }
        return true;
    }

    /**
     * 文件有多少M
     *
     * @param file
     * @return
     */
    public static float getFileSizeOfM(File file) {
        BigDecimal filesize = new BigDecimal(file.length());
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
        if (returnValue > 1) return returnValue;
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
        return returnValue;
    }

    public static float getFileSizeOfK(File file) {
        BigDecimal filesize = new BigDecimal(file.length());
        BigDecimal kilobyte = new BigDecimal(1024);
        float returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
        return returnValue;
    }


    /**
     * 文件大小
     * 1P = 1024T = 1024*1024*1024*1024*1024B
     * 1T = 1024G = 1024*1024*1024*1024B
     * 1G = 1024M = 1024*1024*1024B
     * 1M = 1024K = 1024*1024B
     * 1K = 1024B
     *
     * @param bytes
     * @return
     */
    public static String FileSize2Str(double bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
        if (returnValue > 1) return (returnValue + "MB");
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
        return (returnValue + "KB");
    }

    /**
     * 将字节转换为合适的单位
     *
     * @param size
     * @return
     */
    public static double fileSizeTransform(double size) {
        BigDecimal filesize = new BigDecimal(size);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
        if (returnValue > 1) return returnValue;
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
        return returnValue;
    }

    /**
     * 从uri中获取图片的真实地址
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPathFromUri(Uri uri) {
        Log.e("File -",
                "Authority: " + uri.getAuthority() +
                        ", Fragment: " + uri.getFragment() +
                        ", Port: " + uri.getPort() +
                        ", Query: " + uri.getQuery() +
                        ", Scheme: " + uri.getScheme() +
                        ", Host: " + uri.getHost() +
                        ", Segments: " + uri.getPathSegments().toString()
        );

        String path = null;
        boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(CommonUtil.getAppContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(CommonUtil.getAppContext(), contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(CommonUtil.getAppContext(), contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(CommonUtil.getAppContext(), uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return path;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     * @author paulburke
     */
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                DatabaseUtils.dumpCursor(cursor);
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } catch (IllegalArgumentException ex) {
            Log.e("getDataColumn: _data", ex.getMessage());
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * Gets the extension of a file name, like ".png" or ".jpg".
     *
     * @param uri
     * @return Extension including the dot("."); "" if there is no extension;
     * null if uri was null.
     */
    public static String getExtension(String uri) {
        if (uri == null) {
            return null;
        }

        int dot = uri.lastIndexOf(".");
        if (dot >= 0) {
            return uri.substring(dot);
        } else {
            // No extension.
            return "";
        }
    }

    /**
     * @return The MIME type for the given file.
     */
    public static String getMimeType(File file) {
        String extension = getExtension(file.getName());
        if (extension.length() > 0)
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.substring(1));
        return "application/octet-stream";
    }

    /**
     * @return The MIME type for the give Uri.
     */
    public static String getMimeType(Uri uri) {
        File file = new File(getPathFromUri(uri));
        return getMimeType(file);
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     * @author paulburke
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     * @author paulburke
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     * @author paulburke
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * 文件是否大于6M
     *
     * @param path
     * @return
     */
    public static boolean canUploadFile(String path) {
        float bsize = new File(path).length();
        float msize = Math.round(bsize / 1024 / 1024 * 100) * 0.01f;
        return msize >= 6.00f ? false : true;
    }

    public static void clearDir(File parent) {
//		try {
//			String command = "rm -rf " + parent.getPath();
//			CommonLog.e("command==="+command);
//			Runtime runtime = Runtime.getRuntime();
//			runtime.exec(command);
//		} catch (Exception e) {
//			CommonLog.e(e);
//		}
        try {
            File[] files = parent.listFiles();
            if (null != files) for (File f : files) if (f.exists()) f.delete();
        } catch (Exception e) {
          //  CommonLog.e(e);
        }
    }

    public static synchronized String run(String[] cmd, String workdirectory) throws IOException {
        StringBuffer result = new StringBuffer();
        try {
            // 创建操作系统进程（也可以由Runtime.exec()启动）
            // Runtime runtime = Runtime.getRuntime();
            // Process proc = runtime.exec(cmd);
            // InputStream inputstream = proc.getInputStream();
            ProcessBuilder builder = new ProcessBuilder(cmd);
            InputStream in = null;
            // 设置一个路径（绝对路径了就不一定需要）
            if (workdirectory != null) {
                // 设置工作目录（同上）
                builder.directory(new File(workdirectory));
                // 合并标准错误和标准输出
                builder.redirectErrorStream(true);
                // 启动一个新进程
                Process process = builder.start();
                // 读取进程标准输出流
                in = process.getInputStream();
                byte[] re = new byte[1024];
                while (in.read(re) != -1) {
                    result = result.append(new String(re, "utf-8"));
                }
            }
            // 关闭输入流
            if (in != null) {
                in.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 从file中获取uri兼容api24
     * @param file
     * @return
     */
//	public static Uri getUriFromFile(File file) {
//		Uri uri = null;
//		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
//			uri = FileProvider.getUriForFile(CommonUtil.getAppContext(),"com.xdf.lboc.student.provider", file);
//		}else{
//        	uri = Uri.fromFile(file);
//		}
//		return uri;
//	}

    /**
     * 从路径中获取文件名称
     *
     * @param path
     * @return
     */
    public static String getFileNameFromPath(String path) {
        File file = new File(path);
        return file.getName();
    }


    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
           //     CommonLog.e(e);
            }
        }
        return true;
    }


    public static boolean copyFile(String src, String dst) {
        boolean bresult = false;
        try {
            File in = new File(src);
            File out = new File(dst);
            FileInputStream inFile = new FileInputStream(in);
            FileOutputStream outFile = new FileOutputStream(out);
            byte[] buffer = new byte[1024];

            int len = 0;
            while ((len = inFile.read(buffer)) != -1) {
                outFile.write(buffer, 0, len);
            }
            inFile.close();
            outFile.close();
            bresult = true;

        } catch (IOException localIOException) {
            bresult = false;
        }
        return bresult;
    }

    public static boolean deleteFile(String filepath) {
        File file = new File(filepath);
        if (!file.exists())
            return true;
        return file.delete();
    }

    public static boolean exist(String filepath) {
        if (filepath == null)
            return false;
        return new File(filepath).exists();
    }

    public static void generateOtherImg(String imagePath) {
    }

    public static byte[] getBytesFromFile(File file)
            throws IOException {
        FileInputStream is = new FileInputStream(file);
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            is.close();
            throw new IOException("File is to large " + file.getName());
        }
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        try {
            while ((numRead = is.read(bytes, offset, bytes.length - offset)) > 0) {
                offset += numRead;
            }
            if (offset < length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
        } catch (Exception localException) {
        } finally {
            is.close();
        }
        return bytes;
    }

    public static byte[] getBytesFromFile(String path)
            throws IOException {
        return getBytesFromFile(new File(path));
    }

    public static int getFileLength(String filepath) {
        File file = new File(filepath);
        if (!file.exists())
            return -1;
        return (int) file.length();
    }

    public static String getStrFromFile(File file)
            throws IOException {
        String result = "";
        FileInputStream is = new FileInputStream(file);
        if (file.length() > Integer.MAX_VALUE) {
            is.close();
            throw new IOException("File is too large " + file.getName());
        }
        StringBuffer localStringBuffer = new StringBuffer();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(
                is, "GBK"));
        String str1 = null;
        while ((str1 = localBufferedReader.readLine()) != null) {
            localStringBuffer.append(str1);
            localStringBuffer.append("\n");
        }
        result = localStringBuffer.toString();
        is.close();
        return result;
    }

    public static boolean isDirectory(String filepath) {
        return new File(filepath).isDirectory();
    }

    public static boolean makeSureDirExist(String dirpath) {
        boolean bool = true;
        File file = new File(dirpath);
        if (!file.exists())
            bool = file.mkdir();
        return bool;
    }

    public static boolean makeSureFileExist(String filepath) {
        boolean result = false;
        File localFile = new File(filepath);
        if (localFile.exists())
            return true;
        try {
            result = localFile.createNewFile();
        } catch (IOException localIOException) {
            result = false;
        }
        return result;
    }

    public static int makeSureFileExistEx(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    return 0;
                }
            } catch (IOException e) {
            }
            return -1;
        }
        return (int) file.length();
    }

    public static String newImageName() {
        return UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
    }

    public void finalize() {
    }


    /**
     * 过滤文件名称
     *
     * @param fileName
     * @return
     */
    public static String fileNameFilter(String fileName) {
        // 过滤文件名中的这些字符 \ / : ? * " > < |
        String regEx = "[/:?*\"><|\\\\]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(fileName);
        return m.replaceAll("_").trim();
    }

    public static boolean isContainSpecialCharacters(String string) {
        String regEx = "[/ : * ? \" < >]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }


    /***
     * 根据链接获取文件名（带类型的），具有唯一性
     *
     * @param url
     * @return
     */
    public static String getFileName(String url) {
        String regEx = ".+/(.+)$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(url);
        if (!m.find()) {
            return "";
        }
        return m.group(1);

    }

    /***
     * 获取文件类型
     *
     * @param paramString
     * @return
     */
    public static String getFileType(String paramString) {
        String str = "";
        if (TextUtils.isEmpty(paramString)) {
            return str;
        }
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            return str;
        }
        str = paramString.substring(i + 1);
        return str;
    }


    /**
     * 文件是否存在
     *
     * @param path
     * @return
     */
    public static boolean exists(String path) {
        boolean bFileExist = false;
        try {
            File file = new File(path);
            bFileExist = file.isFile() && file.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bFileExist;
    }

    /**
     * 获取文件大小
     *
     * @param path
     * @return
     */
    public static long getFileSize(String path) {
        long size = 0;
        if (exists(path)) {
            size = new File(path).length();
        }
        return size;
    }
}
