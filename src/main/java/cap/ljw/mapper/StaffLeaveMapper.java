package cap.ljw.mapper;

import cap.ljw.entity.StaffLeave;
import cap.ljw.vo.StaffLeaveVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Ljw
 * 2023.7.14
 * 离开的员工接口
 */
public interface StaffLeaveMapper extends BaseMapper<StaffLeave> {
    @Select("select asl.*,alt.name type_name from att_staff_leave asl inner join att_leave_type alt on asl.type_id = alt.id " +
            "where asl.is_deleted = 0 and asl.staff_id = #{id}")
    IPage<StaffLeaveVO> listStaffLeaveVOByStaffId(IPage<StaffLeaveVO> config, @Param("id") Integer id);

//      不显示撤销的请假申请

    @Select("select asl.*,alt.name type_name,ss.name,ss.code,ss.phone from att_staff_leave asl inner join att_leave_type alt on asl.type_id = alt.id " +
            "inner join sys_staff ss on ss.id = asl.staff_id " +
            "where asl.is_deleted = 0 and asl.status != 3 and ss.name like concat('%',#{name},'%')")
    IPage<StaffLeaveVO> listStaffLeaveVO(IPage<StaffLeaveVO> config, @Param("name") String name);

}
