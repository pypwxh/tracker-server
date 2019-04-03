package com.yfyld.tracker.controller;


import com.yfyld.tracker.model.Analyse;
import com.yfyld.tracker.model.Project;
import com.yfyld.tracker.util.CommonResult;
import com.yfyld.tracker.util.ValidateGroups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(description = "分析相关")
@RestController
@RequestMapping(value = "/analyse")
public class AnalyseController {
    @PostMapping("/")
    @ApiOperation(value = "新增分析")
    public CommonResult addAnalyse(@Validated(ValidateGroups.Default.class) @RequestBody Project project) {
        int count = 1;
        if (count > 0) {
            return new CommonResult().success(null);
        } else {
            return new CommonResult().failed();
        }
    }

    @GetMapping("/{analyseId}")
    @ApiOperation(value = "分析详情")
    public CommonResult getAnalyseInfo(@PathVariable(value="analyseId",required=true) Long analyseId) {
        return new CommonResult().success(null);
    }

    @PutMapping("/{analyseId}")
    @ApiOperation(value = "编辑分析")
    public CommonResult updateAnalyse(@Validated(ValidateGroups.Default.class) @RequestBody Analyse analyse) {
        return new CommonResult().success(null);
    }

    @DeleteMapping("/{analyseId}")
    @ApiOperation(value = "删除分析")
    public CommonResult deleteAnalyse(@PathVariable(value="analyseId",required=true) Long analyseId) {
        return new CommonResult().success(null);
    }
}
