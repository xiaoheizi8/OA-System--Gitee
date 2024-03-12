package cap.ljw.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ljw
 * 2023.7.13
 */
@Getter
@AllArgsConstructor
public enum AttendanceStatusEnum implements BaseEnum {
    //这里的枚举和之前的http🐎一样
    NORMAL(0, "正常"),
    LATE(1, "迟到"),
    LEAVE_EARLY(2, "早退"),
    ABSENTEEISM(3, "旷工"),
    LEAVE(4, "休假");

    @EnumValue
    private final Integer code;
    @JsonValue
    private final String message;
}
