package cap.ljw.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ljw
 * 2023.7.12
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum implements BaseEnum {
    PAY_NO(0, "未支付"),
    PAY_YES(1, "已支付"),
    PAY_FAIl(2, "支付失败");

    @EnumValue
    private final Integer code;
    @JsonValue
    private final String message;
}
