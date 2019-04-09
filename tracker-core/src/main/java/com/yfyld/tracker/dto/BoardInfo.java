package com.yfyld.tracker.dto;

import com.yfyld.tracker.model.Analyse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;



@Setter
@Getter
public class BoardInfo {
    private Long id;
    private String name;
    private String layout;
    private List<Analyse> analyses;
}
