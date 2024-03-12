package cap.ljw.service;

import cap.ljw.dto.Response;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.Dept;
import cap.ljw.entity.Staff;
import cap.ljw.enums.BusinessStatusEnum;
import cap.ljw.exception.ServiceException;
import cap.ljw.mapper.StaffMapper;
import cap.ljw.util.HutoolExcelUtil;
import cap.ljw.util.MD5Util;
import cap.ljw.vo.StaffDeptVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ljw
 * 2023.7.19
 * 员工服务类
 */
@Service
public class StaffService extends ServiceImpl<StaffMapper, Staff> {


    @Resource
    private DeptService deptService;

    @Resource
    private StaffMapper staffMapper;


  //add
    public ResponseDTO add(Staff staff) {
        if (save(staff)) {
            // 设置默认密码、工号、头像
            staff.setPassword(MD5Util.personal("123")).setCode("staff_" + staff.getId()).setAvatar("avatar.png");
            updateById(staff);
            return Response.success();
        }
        return Response.error();
    }
 //删除
    public ResponseDTO deleteById(Integer id) {
        if (removeById(id)) {
            return Response.success();
        }
        return Response.error();
    }

 //编辑
    public ResponseDTO edit(Staff staff) {
        if (updateById(staff)) {
            return Response.success();
        }
        return Response.error();
    }

   //查找
    public ResponseDTO findById(Integer id) {
        Staff staff = getById(id);
        if (staff != null) {
            return Response.success(staff);
        }
        return Response.error();
    }


    /*
     多条件分页查寻
     */
    public ResponseDTO list(Integer current, Integer size, Staff staff) {
        // 分页构造
        IPage<Staff> pageConfig = new Page<>(current, size);
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();
        if (staff.getName() != "" && staff.getName() != null) {
            wrapper.like("name", staff.getName());
        }
        if (staff.getBirthday() != null) {
            wrapper.ge("birthday", staff.getBirthday());
        }
        if (staff.getDeptId() != null) {
            wrapper.eq("dept_id", staff.getDeptId());
        }
        if (staff.getStatus() != null) {
            wrapper.eq("status", staff.getStatus());
        }
        IPage<Staff> page = page(pageConfig, wrapper);
        List<Staff> records = page.getRecords();
        List<StaffDeptVO> staffDeptVOList = new ArrayList<>();
        for (Staff record : records) {
            QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", record.getDeptId());
            Dept dept = this.deptService.getOne(queryWrapper);
            // 设置部门和年龄
            StaffDeptVO staffDeptVO = new StaffDeptVO();
            staffDeptVO.setDeptName(dept.getName());
            if (record.getBirthday() != null) {
                staffDeptVO.setAge(DateUtil.ageOfNow(record.getBirthday()));
            }
            BeanUtil.copyProperties(record, staffDeptVO);
            staffDeptVOList.add(staffDeptVO);
        }
        Map map = new HashMap();
        map.put("pages", page.getPages());
        map.put("total", page.getTotal());
        map.put("list", staffDeptVOList);
        return Response.success(map);
    }

    //批量删除

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO deleteBatch(List<Integer> ids) {
        if (removeBatchByIds(ids)) {
            return Response.success();
        }
        return Response.error();
    }

 //导出
    public ResponseDTO export(HttpServletResponse response) throws IOException {
        List<StaffDeptVO> list = this.staffMapper.findStaffDeptVO();
        // 设置员工年龄
        for (StaffDeptVO staffDeptVO : list) {
            if (staffDeptVO.getBirthday() != null) {
                staffDeptVO.setAge(DateUtil.ageOfNow(staffDeptVO.getBirthday()));
            }
        }
        HutoolExcelUtil.writeExcel(response, list, "员工信息表", StaffDeptVO.class);
        return Response.success();
    }

 //导入
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<Staff> list = HutoolExcelUtil.readExcel(inputStream, 1, Staff.class);
        for (Staff staff : list) {
            if(save(staff)){
                // 设置默认密码、工号、头像、部门
                staff.setPassword(MD5Util.personal("123")).setCode("staff_" + staff.getId()).setAvatar("avatar.png").setDeptId(13);
                updateById(staff);
            }else{
                return Response.error();
            }
        }
        return Response.success();
    }

    // 检查员工的密码
    public ResponseDTO checkPassword(String pwd, Integer id) {
        Staff staff = getById(id);
        if (staff != null) {
            if (StrUtil.isNotBlank(pwd)) {
                if (MD5Util.personal(pwd).equals(staff.getPassword())) {
                    return Response.success();
                }
                throw new ServiceException(BusinessStatusEnum.ERROR.getCode(), "密码错误！");
            }
            throw new ServiceException(500, "密码不能为空！");
        }
        throw new ServiceException(500, "此员工不存在！");
    }

    public ResponseDTO updatePassword(Staff staff) {
        // MD5加密
        staff.setPassword(MD5Util.personal(staff.getPassword()));
        if (updateById(staff)) {
            return Response.success();
        }
        return Response.error();
    }

}
