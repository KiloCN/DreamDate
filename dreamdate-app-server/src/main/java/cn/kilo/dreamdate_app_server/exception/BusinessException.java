package cn.kilo.dreamdate_app_server.exception;

import cn.kilo.dreamdate_model.vo.ErrorResult;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException{
    private ErrorResult errorResult;


    public BusinessException(ErrorResult errorResult) {
        super(errorResult.getErrMessage());
        this.errorResult = errorResult;
    }
}
