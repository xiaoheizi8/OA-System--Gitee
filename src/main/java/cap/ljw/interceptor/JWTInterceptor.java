package cap.ljw.interceptor;

import cap.ljw.entity.Staff;
import cap.ljw.enums.BusinessStatusEnum;
import cap.ljw.exception.ServiceException;
import cap.ljw.service.StaffService;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ljw
 * 2023.7.15
 * 令牌拦截器 实现
 */
public class JWTInterceptor implements HandlerInterceptor {
    @Resource
    private StaffService staffService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         String token=request.getHeader("token");//from http 请求头取出
        if (StrUtil.isNotBlank(token)){
            //get id for 验证staff
            Integer id=Integer.valueOf(JWT.decode(token).getAudience().get(0));
            Staff staff=this.staffService.getById(id);
            if (staff!=null){
                //判断员工是否正常
                if (staff.getStatus()==1){
                    //z验证token
                    JWTVerifier verifier= JWT.require(Algorithm.HMAC256(staff.getPassword())).build();
                    try {
                        verifier.verify(token);//验证token
                    }catch (JWTVerificationException j){
                        throw new ServiceException(BusinessStatusEnum.TOKEN_INVALID);
                    }
                    return true;
                }
                throw  new  ServiceException(BusinessStatusEnum.STAFF_STATUS_ERROR);
            }
            throw new ServiceException(BusinessStatusEnum.NOT_EXIST);
        }
        throw new ServiceException(BusinessStatusEnum.TOKEN_NOT_EXIST);
    }
}
