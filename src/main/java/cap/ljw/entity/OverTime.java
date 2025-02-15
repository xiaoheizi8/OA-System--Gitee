package cap.ljw.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author Ljw
 * 2023.7.11
 * 对于员工来说加班超时资金理想化实体类
 */
@Getter
@Setter
@TableName("att_overtime")
@ApiModel(value = "Overtime对象", description = "加班表")
public class OverTime implements Serializable {
    private static final long serialVersionUID=1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("工资倍数，如按照小时计算，就是员工平均小时工资乘以倍数")
    @TableField("salary_multiple")
    private BigDecimal salaryMultiple;

    @ApiModelProperty("加班奖金")
    @TableField("bonus")
    private BigDecimal bonus;

    @ApiModelProperty("加班类型")
    @TableField("type_id")
    private Integer typeId;

    @ApiModelProperty("部门id")
    @TableField("dept_id")
    private Integer deptId;

    @ApiModelProperty("0小时，1天，默认0，计数加班工资的类型")
    @TableField("count_type")
    private Integer countType;

    @TableField("remark")
    private String remark;

    @ApiModelProperty("1启用，0禁用，默认1")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("0不调休，1调休，默认0")
    @TableField("is_time_off")
    private Integer timeOffFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Timestamp updateTime;

    @ApiModelProperty("逻辑删除，0未删除，1删除")
    @TableField("is_deleted")
    @TableLogic
    private Integer deleteFlag;


}
