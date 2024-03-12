package cap.ljw.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Ljw
 * 2023.7.11
 * 菜单实体类
 */
@Data
@TableName("per_menu")
@ApiModel(value = "Menu对象", description = "")

public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("菜单编码")
    @TableField("code")
    private String code;

    @ApiModelProperty("菜单名称")
    @TableField("name")
    private String name;

    @TableField("icon")
    private String icon;

    @ApiModelProperty("菜单路径")
    @TableField("path")
    private String path;

    @ApiModelProperty("父菜单id，0代表根菜单")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Timestamp updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Integer deleteFlag;

    @TableField(exist = false)
    private List<Menu> children;

}
