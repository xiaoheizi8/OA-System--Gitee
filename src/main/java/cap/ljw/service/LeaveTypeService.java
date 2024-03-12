package cap.ljw.service;

import cap.ljw.dto.Response;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.LeaveType;
import cap.ljw.mapper.LeaveTypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ljw
 * 2023.7.16
 * 请假数据类型服务
 */
@Service
public class LeaveTypeService extends ServiceImpl<LeaveTypeMapper, LeaveType> {


    public ResponseDTO add(LeaveType leaveType) {
        if (save(leaveType)) {
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


    public ResponseDTO edit(LeaveType leaveType) {
        if (updateById(leaveType)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        LeaveType leaveType = getById(id);
        if (leaveType != null) {
            return Response.success(leaveType);
        }
        return Response.error();
    }

    public ResponseDTO findAll() {
        List<LeaveType> list = list();
        return Response.success(list);
    }


}




