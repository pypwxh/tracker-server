package com.yfyld.tracker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Customer {
    private String uuid;
    private String ua;
    private Long uid;
    public Customer(String ua,Long uid){
        this.ua=ua;
        this.uid=uid;
    }
}
