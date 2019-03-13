package com.yfyld.tracker.model;

import com.yfyld.tracker.util.ValidateGroups;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@ToString
public class Project {
    private Long id;
    @NotEmpty(message = "项目名不能为空", groups = ValidateGroups.Default.class)
    private String name;

}
