package com.yfyld.tracker.dto;

import com.yfyld.tracker.util.ValidateGroups;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserLoginParam {
    @ApiModelProperty(value="手机号")
    @NotEmpty(message = "手机号不能为空", groups = ValidateGroups.Default.class)
    private String username;
    @Pattern(regexp = ".{6,}", message = "密码必须是由数字,字母,符号组成且不少于6位字符", groups = ValidateGroups.Default.class)
    @ApiModelProperty(value="密码")
    private String password;
    @ApiModelProperty(value="记住密码")
    private Boolean isRememberMe;
}
