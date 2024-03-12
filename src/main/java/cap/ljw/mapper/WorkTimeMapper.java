package cap.ljw.mapper;

import cap.ljw.entity.WorkTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Ljw
 * 2023.7.15
 * 工作时间表接口
 */
public interface WorkTimeMapper extends BaseMapper<WorkTime> {
    @Select("select att.* from sys_staff ss inner join sys_dept sd on ss.dept_id = sd.id inner join att_work_time att on att.dept_id = sd.id where ss.is_deleted = 0 and ss.id = #{id} ")
    WorkTime findDeptWorkTimeByStaffId(@Param("id") Integer id);
}
