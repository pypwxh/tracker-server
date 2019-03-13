package com.yfyld.tracker.model;


import com.yfyld.tracker.util.ValidateGroups;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 99898909L;


    private Long id;
    @NotEmpty(message = "用户名不能为空", groups = ValidateGroups.Default.class)
    private String username;
    @Pattern(regexp = ".{6,}", message = "密码必须是由数字,字母,符号组成且不少于6位字符", groups = ValidateGroups.Add.class)
    private String password;

    private Boolean rememberMe;

    private Integer status;

    private List<Role> roleList;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

}
