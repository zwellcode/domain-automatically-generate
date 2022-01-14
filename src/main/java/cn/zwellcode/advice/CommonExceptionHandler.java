package cn.zwellcode.advice;

import cn.zwellcode.entities.Result;
import cn.zwellcode.exception.LogicException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 * 处理api中异常
 * controller加强类
 */
@ControllerAdvice
public class CommonExceptionHandler {


    /**
     * 处理自定义逻辑异常
     */
    @ExceptionHandler(LogicException.class)
    @ResponseBody
    public Object logicExceptionHandler(Exception e, HttpServletResponse resp){
        e.printStackTrace();
        resp.setContentType("application/json;charset=utf-8");
        return Result.error(e.getMessage());
    }

    /**
     * 处理服务器异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Object runtimeExceptionHandler(Exception e, HttpServletResponse resp){
        e.printStackTrace();
        resp.setContentType("application/json;charset=utf-8");
        return Result.error(e.getMessage());
    }
}
