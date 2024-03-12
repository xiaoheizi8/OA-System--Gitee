package cap.ljw.mapper;

import cap.ljw.entity.Attendance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;


/**
 * @author Ljw
 * 2023.7.14
 * 接口db
 */
@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance> {
    @Select("select * from att_attendance where is_deleted = 0 and staff_id = #{id} and date_format(attendance_date,'%Y%m%d') = #{day}")
    Attendance findByStaffId(@Param("id") Integer id, @Param("day") String day);

    /*
     * 统计员工迟到、早退、旷工的次数
     */
    @Select("select count(*) from att_attendance where is_deleted = 0 and staff_id = #{id} and status = #{status} and date_format(attendance_date,'%Y%m') = #{month} ")
    Integer countTimes(@Param("id") Integer id, @Param("status") Integer status, @Param("month") String month);


    /*
      查找员工休假的日期
     */
    @Select("select attendance_date from att_attendance where is_deleted = 0 and staff_id = #{id} and status=#{status} and date_format(attendance_date,'%Y%m') = #{month} ")
    List<Date> findLeaveDate(@Param("id") Integer id, @Param("status") Integer status, @Param("month") String month);




}
