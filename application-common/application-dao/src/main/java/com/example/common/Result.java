/*
 * 文 件 名:  Result.java
 * 版    权:  Copyright 2017 南京云蜗信息技术有限公司,  All rights reserved
 * 描    述:  返回值对象
 * 版    本： <1.0.0> 
 * 创 建 人:  Huang Hao
 * 创建时间:  2017年12月11日
 */
package com.example.common;


import java.io.Serializable;
import java.util.Date;

/**
 * 返回值对象
 * @author  Huang Hao
 * @version  [1.0.0, 2017年11月23日]
 * @since  [蜗蜗生活/公共模块]
 */
public class Result implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -5754971731548826123L;

    /** 时间戳. */
    private Long timestamp;
    
    /** 返回码. */
    private String status = "";
    
    /** 结果信息. */
    private String message = "";
    
    /** 结果信息. */
    private Object data;
    
    /**
     * 微服务统一返回对象
     * data：dubbo服务方法返回的对象
     * <默认构造函数>
     */
    public Result(String status, String message, Object data)
    {
        super();
        this.timestamp = new Date().getTime();
        this.status = status;
        this.message = message;
        this.data = data;
    }
    
    /**
     * <默认构造函数>
     */
    public Result()
    {
        super();
    }
    
    //有响应内容的错误返回对象
    public static Result getFailResp(String code, String message, Object resultData)
    {
        return new Result(code, message, resultData);
    }
    
    //有响应内容的错误返回对象
    public static Result getFailResp(String code, String message)
    {
        return new Result(code, message, null);
    }

    //有响应内容的错误返回对象
    public static Result getFailResp( String message)
    {
        return new Result("99999", message, null);
    }
    
    public Long getTimestamp()
    {
        return timestamp;
    }
    
    public void setTimestamp(Long timestamp)
    {
        this.timestamp = timestamp;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public Object getData()
    {
        return data;
    }
    
    public void setData(Object data)
    {
        this.data = data;
    }
    
    //包装成功响应
    public static Result getSuccessResp()
    {
        return getSuccessResp(null);
    }
    
    //包装成功响应
    public static Result getSuccessResp(Object resultData)
    {
        return new Result(ErrorEnum.E_000000.getCode(), ErrorEnum.E_000000.getMessage(), resultData);
    }
    
    //有响应内容的错误返回对象
    public static Result getFailResp(ErrorEnum error, Object resultData)
    {
        return new Result(error.getCode(), error.getMessage(), resultData);
    }
    
    //无响应内容的错误返回对象
    public static Result getFailResp(ErrorEnum error)
    {
        return new Result(error.getCode(), error.getMessage(), null);
    }

    /** {@inheritDoc} */
    
    @Override
    public String toString()
    {
        return "Result [timestamp=" + timestamp + ", status=" + status + ", message=" + message + ", data=" + data
            + "]";
    }
}
