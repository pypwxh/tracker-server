package com.yfyld.tracker.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@Getter
public class Board {
    private Long id;
    @NotEmpty(message = "名不能为空")
    private String name;
    private String layout="";
}
