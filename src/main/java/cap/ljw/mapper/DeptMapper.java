package cap.ljw.mapper;

import cap.ljw.entity.Dept;
import cap.ljw.vo.DeptWorkTimeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Ljw
 * 2023.7.13
 */
public interface DeptMapper extends BaseMapper<Dept> {
    @Select("select sd.*,awt.id work_time_id, awt.mor_start_time,awt.mor_end_time,awt.aft_start_time,awt.aft_end_time,awt.total_work_time " + "from sys_dept sd left join att_work_time awt on sd.id = awt.dept_id " + "where sd.is_deleted = 0 and sd.parent_id = 0 and sd.name like concat('%',#{name},'%')")
    IPage<DeptWorkTimeVO> listParentDeptWorkTimeVO(IPage<DeptWorkTimeVO> config, @Param("name") String name);

    /**
     * 查找所有子部门
     *
     * @return
     */
    @Select("select sd.*,awt.id work_time_id, awt.mor_start_time,awt.mor_end_time,awt.aft_start_time,awt.aft_end_time,awt.total_work_time " + "from sys_dept sd left join att_work_time awt on sd.id = awt.dept_id where sd.is_deleted = 0 and sd.parent_id != 0")
    List<DeptWorkTimeVO> findSubDeptWorkTimeVO();}
