package com.yfyld.tracker.util;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@ToString
public class CommonResult {
    public static final int SUCCESS = 200;
    public static final int FAILED = 500;
    public static final int VALIDATE_FAILED = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    private int code;
    private String message;
    private Object result;



    public CommonResult success(Object data){
        this.code= SUCCESS;
        this.message="操作成功";
        this.result =data;
        return this;
    }

    public CommonResult pageSuccess(List data){
        PageInfo pageInfo = new PageInfo(data);
        long totalPage = pageInfo.getTotal()/pageInfo.getPageSize();
        Map<String,Object> result = new HashMap<>();
        result.put("totalPage",totalPage);
        result.put("list",pageInfo.getList());
        this.code=SUCCESS;
        this.message="操作成功";
        this.result =result;
        return this;
    }

    public CommonResult failed() {
        this.code = FAILED;
        this.message = "操作失败";
        return this;
    }


    public CommonResult validateFailed(String message){
        this.code=VALIDATE_FAILED;
        this.message=message;
        return this;
    }

    public CommonResult unauthorized(String message) {
        this.code = UNAUTHORIZED;
        this.message = "未登录或token已经过期";
        return this;
    }

    public CommonResult forbidden(String message) {
        this.code = FORBIDDEN;
        this.message = "没有相关权限";
        return this;
    }



}
