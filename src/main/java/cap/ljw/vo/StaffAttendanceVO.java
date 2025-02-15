package cap.ljw.vo;

import cap.ljw.annotation.ExcelColumn;
import cap.ljw.entity.Attendance;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ljw
 * 2023.7.13
 * 员工出勤工具
 */
@Data
public class StaffAttendanceVO implements Serializable {
    private static final long serialVersionUID=1L;
    @ApiModelProperty("员工id")
    private Integer staffId;

    @ApiModelProperty("部门id")
    private Integer deptId;

    @ExcelColumn("员工工号")
    @ApiModelProperty("员工工号")
    private String code;

    @ExcelColumn("员工姓名")
    @ApiModelProperty("员工姓名")
    private String name;

    @ExcelColumn("电话")
    @ApiModelProperty("电话")
    private String phone;

    @ExcelColumn("地址")
    @ApiModelProperty("地址")
    private String address;

    @ExcelColumn("部门")
    @ApiModelProperty("部门")
    private String deptName;

    @ApiModelProperty("员工考勤数据")
    private List<Attendance> attendanceList;

}
