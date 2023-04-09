package cn.kilo.dreamdate_app_server.exception;


import cn.kilo.dreamdate_model.vo.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ExceptionAdvice class for handling global exceptions
 * @author Kilo.CN
 */
@ControllerAdvice
public class ExceptionAdvice {

    //处理业务异常
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handlerException(BusinessException be) {
        be.printStackTrace();
        ErrorResult errorResult = be.getErrorResult();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResult);
    }

    //处理不可预知的异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerException1(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResult.error());
    }
}