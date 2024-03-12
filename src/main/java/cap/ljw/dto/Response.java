package cap.ljw.dto;

import cap.ljw.enums.BaseEnum;
import cap.ljw.enums.BusinessStatusEnum;

/**
 * @author Ljw
 * 2023.7.12
 * dto层数据传输对象响应
 * 这里是请求响应数据
 */
public class Response {
    //合理利用枚举的方式去实现,static静态访问
    public static ResponseDTO success(){
        return new ResponseDTO(BusinessStatusEnum.SUCCESS);
    }

    public static ResponseDTO success(String message){
        return new ResponseDTO(BusinessStatusEnum.SUCCESS.getCode(), message);
    }

    public static ResponseDTO success(Object data){
        return new ResponseDTO(BusinessStatusEnum.SUCCESS,data);
    }

    public static ResponseDTO success(Object data,String token){
        return new ResponseDTO(BusinessStatusEnum.SUCCESS,data,token);
    }

    public static ResponseDTO success(String message, Object data){
        return new ResponseDTO(BusinessStatusEnum.SUCCESS.getCode(),message,data);
    }

    public static ResponseDTO error(String message){
        return new ResponseDTO(BusinessStatusEnum.ERROR.getCode(), message);
    }

    public static ResponseDTO error(){
        return new ResponseDTO(BusinessStatusEnum.ERROR);
    }

    public static ResponseDTO error(Integer code, String message){
        return new ResponseDTO(code,message);
    }

    public static ResponseDTO error(BaseEnum e) {
        return new ResponseDTO(e);
    }
}
