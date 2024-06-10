package com.atguigu.ssyx.common.exception;

import com.atguigu.ssyx.common.exception.SsyxException;
import com.atguigu.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: GlobalExceprionHandler
 * Package: com.atguigu.ssyx.common.exception
 * Description:
 *
 * @Author liuux
 * @Create 2024/1/11 13:45
 * @Version 1.0
 */

// AOP面向切面编程思想(嵌入到代码中，不改变源代码增加一个功能)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 异常处理器，指定出现何种异常时执行（现在只能接收到系统提供的异常，自己定义异常重新写）
    @ResponseBody // 返回json数据
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail(null);
    }

    /**
     * 3. 自定义异常处理方法
     */
    @ExceptionHandler(SsyxException.class)
    @ResponseBody
    public Result error(SsyxException ssyxException) {
        return Result.build(null, ssyxException.getCode(), ssyxException.getMessage());
//        return Result.ok(null);
    }


}