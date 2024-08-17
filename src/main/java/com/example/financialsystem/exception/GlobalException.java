package com.example.financialsystem.exception;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/15 13:30
 */

            import com.example.financialsystem.common.Result;
            import org.springframework.http.HttpStatus;
            import org.springframework.http.converter.HttpMessageNotReadableException;
            import org.springframework.web.HttpRequestMethodNotSupportedException;
            import org.springframework.web.bind.annotation.ControllerAdvice;
            import org.springframework.web.bind.annotation.ExceptionHandler;
            import org.springframework.web.bind.annotation.ResponseBody;
            import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result serviceException(ServiceException e) {
        return Result.error(e.getCode(), e.getMessage());



    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        // 这里可以添加更复杂的逻辑，例如解析异常信息中的具体字段，以返回更具体的错误信息
        String errorMessage = "输入的数据格式不正确，请检查您的输入。";
        return Result.error("400");
    }
}
