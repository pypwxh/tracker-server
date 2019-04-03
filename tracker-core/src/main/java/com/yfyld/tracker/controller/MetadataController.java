package com.yfyld.tracker.controller;

import com.yfyld.tracker.dto.GetMetadataListParam;
import com.yfyld.tracker.model.Metadata;
import com.yfyld.tracker.service.event.MetadataService;
import com.yfyld.tracker.util.CommonResult;
import com.yfyld.tracker.util.ValidateGroups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Api(description = "元数据")
@RestController
@RequestMapping(value = "/metadata")
public class MetadataController {

    @Autowired
    MetadataService metadataService;

    @PostMapping("/")
    @ApiOperation(value = "新增元数据")
    public CommonResult addMetadata(@Validated(ValidateGroups.Default.class) @RequestBody Metadata metadata,@RequestHeader(value="projectId") Long projectId) {
        metadata.setProjectId(projectId);
        int count = metadataService.addMetadata(metadata);
        if (count > 0) {
            return new CommonResult().success(null);
        } else {
            return new CommonResult().failed();
        }
    }

    @GetMapping("/")
    @ApiOperation(value = "元数据搜索")
    public CommonResult getMetadataList(@Validated GetMetadataListParam params,
            @RequestHeader(value="projectId") Long projectId
    ) {
        params.setProjectId(projectId);
        return new CommonResult().pageSuccess(metadataService.getMetadataList(params));
    }

    @GetMapping("/{metadataId}")
    @ApiOperation(value = "元数据详情")
    public CommonResult getMetadataInfo(@PathVariable(value="metadataId",required=true) Long metadataId) {
        return new CommonResult().success(metadataService.getMetadataInfo(metadataId));
    }


    @PutMapping("/{metadataId}")
    @ApiOperation(value = "编辑元数据")
    public CommonResult updateMetadata(@Validated(ValidateGroups.Default.class) @RequestBody Metadata analyse) {
        return new CommonResult().success(null);
    }


    @DeleteMapping("/{metadataId}")
    @ApiOperation(value = "删除元数据")
    public CommonResult deleteMetadata(@PathVariable(value="metadataId",required=true) Long metadataId) {
        return new CommonResult().success(null);
    }
}
