package com.yfyld.tracker.model;

import com.yfyld.tracker.util.ValidateGroups;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@ToString
public class Metadata {
    private Long id;
    @NotEmpty(message = "元数据名不能为空", groups = ValidateGroups.Add.class)
    private Long projectId;
    @NotEmpty(message = "元数据名不能为空", groups = ValidateGroups.Add.class)
    private String name;
    @NotEmpty(message = "元数据名不能为空", groups = ValidateGroups.Add.class)
    private String desc;
    @NotEmpty(message = "元数据名不能为空", groups = ValidateGroups.Add.class)
    private String code;
    private Integer status;

}
