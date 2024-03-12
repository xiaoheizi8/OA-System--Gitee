package cap.ljw.util;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * @author Ljw
 * 2023.7.15
 * Jwt工具类采取令牌jwt机制
 */
public class JWTUtil {
    //生成token
  public static String  generateToken(Integer id,String password){
      return JWT.create().withAudience(id.toString())//设置负荷
              .withExpiresAt(DateUtil.offsetHour(new Date(),1))//设置过期时间
              .sign(Algorithm.HMAC256(password));//signature签名
  }
}
