package cap.ljw.service;

import cap.ljw.dto.Response;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.Leave;
import cap.ljw.vo.LeaveVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cap.ljw.mapper.LeaveMapper;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ljw
 * 请假服务
 * 2023.7.16
 */
@Service
public class LeaveService extends ServiceImpl<LeaveMapper, Leave> {


    @Resource
    private LeaveMapper leaveMapper;


    public ResponseDTO add(Leave leave) {
        if (save(leave)) {
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


    public ResponseDTO edit(Leave leave) {
        if (updateById(leave)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        Leave leave = getById(id);
        if (leave != null) {
            return Response.success(leave);
        }
        return Response.error();
    }

    public ResponseDTO find(Integer deptId, Integer typeId) {
        QueryWrapper<Leave> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", deptId).eq("type_id", typeId);
        Leave leave = getOne(queryWrapper);
        if (leave != null) {
            return Response.success(leave);
        }
        return Response.error();
    }


    public ResponseDTO setLeave(Leave leave) {
        QueryWrapper<Leave> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", leave.getDeptId()).eq("type_id", leave.getTypeId());
        if (saveOrUpdate(leave, queryWrapper)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findByDeptId(Integer id) {
        List<LeaveVO> list = this.leaveMapper.findByDeptId(id);
        return Response.success(list);
    }
}




