package com.atguigu.ssyx.common.result;

import com.atguigu.ssyx.common.exception.SsyxException;
import lombok.Data;

@Data
public class Result<T> {

    // 状态码
    private Integer code;
    // 信息
    private String message;
    // 数据
    private T data;

    // 构造私有化
    private Result() { }

    // 设置数据，返回对象的方法
    public static<T> Result<T> build(T data, Integer code, String message) {
        // 创建Result对象。设置值。返回对象
        Result<T> result = new Result<>();

        // 判断返回结果中是否需要数据
        if (data != null) {
            result.setData(data);
        }
        // 设置其他信息
        result.setCode(code);
        result.setMessage(message);

        // 返回设置值之后的对象
        return result;
    }
    public static<T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        // 创建Result对象。设置值。返回对象
        Result<T> result = new Result<>();

        // 判断返回结果中是否需要数据
        if (data != null) {
            result.setData(data);
        }
        // 设置其他信息
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());

        // 返回设置值之后的对象
        return result;
    }

    // 现在已经可以正常调用了，为了便捷
    // 成功
    public static<T> Result<T> ok(T data) {
        Result<T> result = build(data, ResultCodeEnum.SUCCESS);
        return result;
    }
    // 失败
    public static<T> Result<T> fail(T data) {
        Result<T> result = build(data, ResultCodeEnum.FAIL);
        return result;
    }

}