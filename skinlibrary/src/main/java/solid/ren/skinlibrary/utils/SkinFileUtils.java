package solid.ren.skinlibrary.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.githang.android.snippet.util.IOUtils;
import com.githang.android.snippet.util.TextUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

import solid.ren.skinlibrary.SkinConfig;

/**
 * @author _SOLID
 * @author Geek_Soledad (http://github.com/msdx/)
 * @since 2016/7/5 10:17
 */
public class SkinFileUtils {

    private static final String CHECK_ALGORITHM = "MD5";
    private static final int BUFFER_SIZE = 1024;

    /**
     * 复制assets/skin目录下的皮肤文件到指定目录
     *
     * @param context the context
     * @param name    皮肤名
     * @param toDir   指定目录
     * @return
     */
    public static String copySkinAssetsToDir(Context context, String name, String toDir) {
        String toFile = toDir + File.separator + name;
        try {
            InputStream is = context.getAssets().open(SkinConfig.SKIN_DIR_NAME + File.separator + name);
            File fileDir = new File(toDir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            OutputStream os = new FileOutputStream(toFile);
            int byteCount;
            byte[] bytes = new byte[1024];

            while ((byteCount = is.read(bytes)) != -1) {
                os.write(bytes, 0, byteCount);
            }
            os.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return toFile;
    }

    /**
     * 得到存放皮肤的目录
     *
     * @param context the context
     * @return 存放皮肤的目录
     */
    public static String getSkinDir(Context context) {
        File skinDir = new File(getCacheDir(context), SkinConfig.SKIN_DIR_NAME);
        if (!skinDir.exists()) {
            skinDir.mkdirs();
        }
        return skinDir.getAbsolutePath();
    }

    /**
     * 得到手机的缓存目录
     *
     * @param context
     * @return
     */
    public static String getCacheDir(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = context.getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir.getAbsolutePath();
            }
        }

        File cacheDir = context.getCacheDir();

        return cacheDir.getAbsolutePath();
    }

    public static boolean isSameFile(Context context, String skinFileName, File targetFile) {
        try {
            InputStream source = context.getAssets().open(SkinConfig.SKIN_DIR_NAME + File.separator + skinFileName);
            InputStream target = new FileInputStream(targetFile);
            return TextUtils.equals(md5File(source), md5File(target));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String md5File(InputStream is) {
        try {
            MessageDigest digest = MessageDigest.getInstance(CHECK_ALGORITHM);
            int length;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((length = is.read(buffer)) != -1) {
                digest.update(buffer, 0, length);
            }
            byte[] result = digest.digest();
            return TextUtil.byteArrayToHexString(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(is);
        }
        return null;
    }
}
