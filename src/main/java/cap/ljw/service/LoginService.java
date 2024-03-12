package cap.ljw.service;

import cap.ljw.dto.Response;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.Staff;
import cap.ljw.enums.BusinessStatusEnum;
import cap.ljw.mapper.StaffMapper;
import cap.ljw.util.JWTUtil;
import cap.ljw.vo.StaffDeptVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import cap.ljw.util.MD5Util;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ljw
 * 2023.7.17
 * 登录业务
 */

@Service
public class LoginService extends ServiceImpl<StaffMapper, Staff> {

    @Resource
    private StaffMapper staffMapper;

    public ResponseDTO login(Staff staff) {
        String password = MD5Util.personal(staff.getPassword());
        StaffDeptVO staffDeptVO = this.staffMapper.findStaffInfo(staff.getCode(), password);
        if (staffDeptVO != null) {
            // 验证用户状态
            if (staffDeptVO.getStatus() == 1) {
                String token = JWTUtil.generateToken(staffDeptVO.getId(),password);
                return Response.success(staffDeptVO, token); // 返回员工信息和token
            }
            return Response.error(BusinessStatusEnum.STAFF_STATUS_ERROR);
        }
        return Response.error("用户名或密码错误！");
    }


    public ResponseDTO findByCode(String code) {
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();
        wrapper.eq("code", code);
        List<Staff> staffList = list(wrapper);
        if (staffList.size() > 0) {
            return Response.success();
        }
        return Response.error();
    }
}
