package com.yfyld.tracker.event.constroller;

import com.yfyld.tracker.util.CommonResult;
import com.yfyld.tracker.event.dto.EventParam;


import com.yfyld.tracker.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Api(description = "获取日志")
@RestController
@RequestMapping(value = "/event")
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger("GET_EVENT");

    @ApiOperation(value = "获取日志", notes = "获取日志")
    @GetMapping("/event.gif")
    public CommonResult getEventByGif(@RequestBody EventParam event, BindingResult bindingResult) {
        LOGGER.info("{}", JsonUtil.objectToJson(event));
        return new CommonResult().success(null);
    }

    @ApiOperation(value = "获取日志", notes = "获取日志")
    @PostMapping("/")
    public CommonResult getEvent(@RequestBody EventParam event, BindingResult bindingResult) {
        LOGGER.info("{}", JsonUtil.objectToJson(event));
        return new CommonResult().success(null);
    }

}
