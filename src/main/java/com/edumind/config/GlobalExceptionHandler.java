package com.edumind.config;

import com.edumind.common.AjaxResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult handleValidationException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldError().getDefaultMessage();
        return AjaxResult.error("Parameter validation failed:" + msg);
    }

    @ExceptionHandler(RuntimeException.class)
    public AjaxResult handleRuntimeException(RuntimeException e) {
        return AjaxResult.error("System abnormality:" + e.getMessage());
    }
}