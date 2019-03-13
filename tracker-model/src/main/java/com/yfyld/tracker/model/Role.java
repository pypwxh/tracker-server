package com.yfyld.tracker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Role {
    private Long id;
    private String roleCode;
    private String roleName;
    private List<Permission> permissionList;

    public Role(String roleCode,String roleName){
        this.roleCode=roleCode;
        this.roleName=roleName;
    }
}
