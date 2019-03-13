package com.yfyld.tracker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long pid;
    private String name;
    private String value;
    private Integer type;
    private String uri;
    private Integer status;
    private Date createTime;
    private Integer sort;


}
