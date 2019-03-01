package cn.fan.cloud_note.utils;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.MessageDigest;
import java.util.UUID;


/**
 * 常用工具类
 */
public abstract class Utils {

    /*
     * 利用UUID算法生成主键
     */
    public static String createId() {
        UUID uuid=UUID.randomUUID();
        String id=uuid.toString().replace("-","");
        return id;
    }

    /*
     * 使用MD5加密处理
     */
    public static String md5(String src) {
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            //MD5加密处理
            byte[] output=md.digest(src.getBytes());
            //Base64处理
            String ret=Base64.encode(output);
            return ret;
        }catch (Exception e) {
            throw new RuntimeException("MD5加密失败",e);
        }
    }

}
