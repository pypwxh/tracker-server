package com.yfyld.tracker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Analyse {
    private Long id;
    private String name;
    private Integer type;
    private Long projectId;
    private String filter;
    private Integer timeType;
    private Date startDate;
    private Date endDate;
    private Integer chartType;
    private String dimension;
}
