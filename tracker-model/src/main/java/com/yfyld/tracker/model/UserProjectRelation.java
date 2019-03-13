package com.yfyld.tracker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserProjectRelation {
    private Long id;
    private Long userId;
    private Long projectId;
    private Integer isAdmin;
}
