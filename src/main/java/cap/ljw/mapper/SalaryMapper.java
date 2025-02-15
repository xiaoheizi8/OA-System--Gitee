package cap.ljw.mapper;

import cap.ljw.entity.Salary;
import cap.ljw.vo.StaffSalaryVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 员工工资表 Mapper 接口
 * </p>
 *
 * @author ljw
 * @since 2023-08-15
 */
public interface SalaryMapper extends BaseMapper<Salary> {
    @Select("select ss.id staff_id,ss.dept_id,ss.code,ss.name,ss.phone,ss.address,sd.name dept_name,si.per_social_pay social_pay,si.per_house_pay house_pay " +
            "from sys_staff ss inner join sys_dept sd on ss.dept_id = sd.id left join soc_insurance si on ss.id = si.staff_id where ss.is_deleted = 0")
    List<StaffSalaryVO> findStaffSalaryVO();


    @Select("select ss.id staff_id,ss.dept_id,ss.code,ss.name,ss.phone,ss.address,sd.name dept_name,si.per_social_pay social_pay,si.per_house_pay house_pay " +
            "from sys_staff ss inner join sys_dept sd on ss.dept_id = sd.id left join soc_insurance si on ss.id = si.staff_id where ss.is_deleted = 0 and ss.name like concat('%',#{name},'%')")
    IPage<StaffSalaryVO> listStaffSalaryVO(IPage<StaffSalaryVO> config, @Param("name") String name);

}
