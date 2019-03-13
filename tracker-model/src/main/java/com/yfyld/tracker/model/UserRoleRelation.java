package com.yfyld.tracker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRoleRelation {
    private Long id;
    private Long roleId;
    private Long userId;
}
