package com.yfyld.tracker.dto;

import com.yfyld.tracker.util.ValidateGroups;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class GetMetadataListParam {
    @ApiModelProperty(value="元数据查询")
    private Long projectId;
    @ApiModelProperty(value="元数据查询")
    @NotNull(message = "分页不能为空")
    private Integer page;
    @NotNull(message = "分页不能为空")
    @ApiModelProperty(value="分页")
    private Integer pageSize;
    @ApiModelProperty(value="埋点code")
    private String code;
    @ApiModelProperty(value="标签")
    private Long[] tags;
    @ApiModelProperty(value="状态1启动0禁用")
    private Integer status;
    @ApiModelProperty(value="名称")
    private String name;
}
