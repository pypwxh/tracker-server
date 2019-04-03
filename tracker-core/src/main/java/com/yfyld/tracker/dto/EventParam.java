package com.yfyld.tracker.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventParam {
    @ApiModelProperty(value="id")
    private Long id;
    @ApiModelProperty(value="uid")
    private Long uid;
    @ApiModelProperty(value="projectId")
    private Long projectId;
    @ApiModelProperty(value="创建时间")
    private Long create_time;
    @ApiModelProperty(value="ua")
    private String ua;
    @ApiModelProperty(value="ip")
    private String ip;
    @ApiModelProperty(value="type")
    private String type;
    @ApiModelProperty(value="code")
    private String code;
    @ApiModelProperty(value="uuid")
    private String uuid;
    @ApiModelProperty(value="newUser")
    private Boolean newUser=false;

}
