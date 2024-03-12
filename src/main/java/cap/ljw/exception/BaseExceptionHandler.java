package cap.ljw.exception;

import cap.ljw.dto.Response;
import cap.ljw.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ljw
 * 2023.7.12
 * 事务处理开启
 */
@ControllerAdvice
public class BaseExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);
@ExceptionHandler(ServiceException.class)
    @ResponseBody
public ResponseDTO handle(ServiceException exception){
    logger.info(exception.getMessage());
    return Response.error(exception.getCode(),exception.getMessage());
}
}
