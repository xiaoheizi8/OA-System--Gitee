package cap.ljw.service;

import cap.ljw.dto.Response;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.OverTimeType;
import cap.ljw.mapper.OverTimeTypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ljw
 * 2023.7.18
 * 加班类型费力不讨好
 */
@Service
public class OvertimeTypeService extends ServiceImpl<OverTimeTypeMapper, OverTimeType> {


    public ResponseDTO add(OverTimeType overtimeType) {
        if (save(overtimeType)) {
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


    public ResponseDTO edit(OverTimeType overtimeType) {
        if (updateById(overtimeType)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        OverTimeType overtimeType = getById(id);
        if (overtimeType != null) {
            return Response.success(overtimeType);
        }
        return Response.error();
    }


    public ResponseDTO findAll() {
        List<OverTimeType> list = list();
        return Response.success(list);
    }
}




