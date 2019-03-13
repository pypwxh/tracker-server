package com.yfyld.tracker.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebLog {
    private String description;
    private String username;
    private Long startTime;
    private Integer spendTime;
    private String basePath;
    private String uri;
    private  String url;
    private String method;
    private String ip;
    private Object parameter;
    private Object result;
}
