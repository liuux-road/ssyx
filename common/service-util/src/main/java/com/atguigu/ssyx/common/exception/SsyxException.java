package com.atguigu.ssyx.common.exception;

import com.atguigu.ssyx.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * ClassName: SsyxException
 * Package: com.atguigu.ssyx.common.exception
 * Description:
 *      自定义异常类
 *      1. 创建异常类，继承RuntimeException
 *      2. 在异常类中定义属性，生成get set方法
 *      3. 在全局异常类定义自定义异常执行的方法
 *      4. 手动抛出异常（代码中：throw new 自定义异常）
 *
 * @Author liuux
 * @Create 2024/1/11 13:46
 * @Version 1.0
 */
@Data
public class SsyxException extends RuntimeException {

    // 异常状态码
    private Integer code;

    /**
     * 方法一：通过状态码和错误消息创建异常对象
     * @param message
     * @param code
     */
    public SsyxException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 方法二：接受枚举类型对象创建异常对象
     * @param resultCodeEnum
     */
    public SsyxException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
