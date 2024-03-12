package cap.ljw.service;
import cap.ljw.dto.Response;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.OverTime;
import cap.ljw.mapper.OverTimeMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;

 /**
 * @author Ljw
 * 2023.7.17
 * 加班的服务类
 */
@Service
public class OvertimeService extends ServiceImpl<OverTimeMapper, OverTime> {


    public ResponseDTO add(OverTime overtime) {
        if (save(overtime)) {
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

    public ResponseDTO deleteBatch(List<Integer> ids) {
        if (removeBatchByIds(ids)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO edit(OverTime overtime) {
        if (updateById(overtime)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        OverTime overtime = getById(id);
        if (overtime != null) {
            return Response.success(overtime);
        }
        return Response.error();
    }

    public ResponseDTO find(Integer deptId, Integer typeId) {
        QueryWrapper<OverTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", deptId).eq("type_id", typeId);
        OverTime overtime = getOne(queryWrapper);
        if (overtime != null) {
            return Response.success(overtime);
        }
        return Response.error();
    }


    public ResponseDTO setOvertime(OverTime overtime) {
        QueryWrapper<OverTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", overtime.getDeptId()).eq("type_id", overtime.getTypeId());
        if (saveOrUpdate(overtime, queryWrapper)) {
            return Response.success();
        }
        return Response.error();
    }


}




