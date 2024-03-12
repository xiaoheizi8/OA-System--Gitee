package cap.ljw.service;



import cap.ljw.dto.Response;
import cap.ljw.dto.ResponseDTO;
import cap.ljw.entity.DeductType;
import cap.ljw.mapper.DeductTypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ljw
 * 2023.7.16
 * 赔钱货服务
 */
@Service
public class DeductTypeService extends ServiceImpl<DeductTypeMapper, DeductType> {


    public ResponseDTO add(DeductType deductType) {
        if (save(deductType)) {
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


    public ResponseDTO edit(DeductType deductType) {
        if (updateById(deductType)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        DeductType deductType = getById(id);
        if (deductType != null) {
            return Response.success(deductType);
        }
        return Response.error();
    }


    public ResponseDTO findAll() {
        List<DeductType> list = list();
        return Response.success(list);

    }
}




