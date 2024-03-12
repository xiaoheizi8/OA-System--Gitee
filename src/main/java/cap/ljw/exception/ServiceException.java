package cap.ljw.exception;

import cap.ljw.enums.BaseEnum;
import lombok.Getter;

/**
 * @author Ljw
 * 2023.7.11
 * 既然是BS架构那么肯定少不了自定义异常处理在表示层
 */
@Getter
public class ServiceException extends RuntimeException {
    private Integer code;
    public ServiceException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public ServiceException(BaseEnum e){
        super(e.getMessage());
        this.code = e.getCode();
    }

}
