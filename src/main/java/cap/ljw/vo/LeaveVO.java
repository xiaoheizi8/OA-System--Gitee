package cap.ljw.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Ljw
 * 2023.7.12员工离开的工具类
 */
@Data
public class LeaveVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("假期名称")
    private String name;

    @ApiModelProperty("休假类型id")
    private Integer typeId;

    @ApiModelProperty("部门id")
    private Integer deptId;

    @ApiModelProperty("休假天数")
    private Integer days;

    @ApiModelProperty("0禁用，1正常，默认1")
    private Integer status;

}
