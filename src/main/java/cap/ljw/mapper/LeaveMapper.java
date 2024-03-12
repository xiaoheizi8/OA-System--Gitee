package cap.ljw.mapper;
import cap.ljw.entity.Leave;
import cap.ljw.vo.LeaveVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @author Ljw
 * 2023.7.14
 * 请假表 URL>>DB
 */
public interface LeaveMapper extends BaseMapper <Leave>{
    @Select("select al.id,al.days,al.status,al.type_id,al.dept_id,alt.name from att_leave al inner join att_leave_type alt on al.type_id = alt.id where al.is_deleted = 0 and al.dept_id = #{id}")
    List<LeaveVO> findByDeptId(@Param("id") Integer id);

}
