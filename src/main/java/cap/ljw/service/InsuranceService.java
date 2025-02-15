package cap.ljw.service;

import cap.ljw.dto.Response;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.Insurance;
import cap.ljw.enums.BusinessStatusEnum;
import cap.ljw.exception.ServiceException;
import cap.ljw.util.HutoolExcelUtil;
import cap.ljw.vo.StaffInsuranceVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
 import cap.ljw.mapper.InsuranceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ljw
 * 2023.7.16.
 * 保险业务服务
 */
@Service
public class InsuranceService extends ServiceImpl<InsuranceMapper, Insurance> {

    @Resource
    private InsuranceMapper insuranceMapper;

    public ResponseDTO add(Insurance insurance) {
        if (save(insurance)) {
            return Response.success();
        }
        return Response.error();
    }

    public ResponseDTO deleteById(Integer id) {
        if (removeById(id)) {
            return Response.success();
        }
        return Response.error();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO deleteBatch(List<Integer> ids) {
        if (removeBatchByIds(ids)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO edit(Insurance insurance) {
        if (updateById(insurance)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        Insurance insurance = getById(id);
        if (insurance != null) {
            return Response.success(insurance);
        }
        return Response.error();
    }


    public ResponseDTO list(Integer current, Integer size, String name) {
        IPage<StaffInsuranceVO> config = new Page<>(current, size);
        IPage<StaffInsuranceVO> page = this.insuranceMapper.listStaffInsuranceVO(config, name);
        // 将响应数据填充到map中
        Map map = new HashMap();
        map.put("pages", page.getPages());
        map.put("total", page.getTotal());
        map.put("list", page.getRecords());
        return Response.success(map);
    }

    //数据导出

    public ResponseDTO export(HttpServletResponse response) throws IOException {
        List<StaffInsuranceVO> list = this.insuranceMapper.findStaffInsuranceVO();
        HutoolExcelUtil.writeExcel(response, list, "员工五险一金表", StaffInsuranceVO.class);
        return Response.success();
    }

    //file数据导入

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<Insurance> list = HutoolExcelUtil.readExcel(inputStream, 1, Insurance.class);
        // IService接口中的方法.批量插入数据
        if (saveBatch(list)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO setInsurance(Insurance insurance) {
        QueryWrapper<Insurance> queryWrapper = new QueryWrapper();
        queryWrapper.eq("staff_id", insurance.getStaffId());
        if (saveOrUpdate(insurance, queryWrapper)) {
            return Response.success(BusinessStatusEnum.SUCCESS);
        }
        throw new ServiceException(BusinessStatusEnum.ERROR);
    }

    public ResponseDTO findByStaffId(Integer id) {
        QueryWrapper<Insurance> queryWrapper = new QueryWrapper();
        queryWrapper.eq("staff_id", id);
        Insurance insurance = getOne(queryWrapper);
        if (insurance == null) {
            return Response.error();
        }
        return Response.success(insurance);
    }
}




