package cap.ljw.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author Ljw
 * 2023.7.13
 * MD5数据加密 static!!!!!!!!
 */
public class MD5Util {
    private static String toHex(byte[] bytes) {
        //字节数组转化为十六进制数组
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            ret.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[aByte & 0x0f]);
        }
        return ret.toString();
    }
    public static String MD(String str){
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            byte[] bytes= md.digest(str.getBytes(StandardCharsets.UTF_8));
            return toHex(bytes);
        }catch (Exception e){
            throw  new RuntimeException(e);//丢出去
        }
    }
    //处理加密的密码
    public static String personal(String str){
        //MD5加密
        String pwd=MD(str);
        //破坏密文结构从首字母开始截取密码字符到第四位,以及从第29位开始截取到末尾,20-29，4-20的前一个字符
        return pwd.substring(0,4)+pwd.substring(29)+pwd.substring(20,29)+pwd.substring(4,20);
    }
}
