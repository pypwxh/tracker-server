package com.yfyld.tracker.controller;

import com.yfyld.tracker.model.Customer;
import com.yfyld.tracker.service.event.CustomerService;
import com.yfyld.tracker.util.CommonResult;
import com.yfyld.tracker.dto.EventParam;


import com.yfyld.tracker.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Api(description = "获取日志")
@RestController
@RequestMapping(value = "/event")
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger("GET_EVENT");

    @Autowired
    CustomerService customerService;

    @ApiOperation(value = "获取日志")
    @GetMapping("/event.gif")
    public CommonResult getEventByGif(@RequestBody EventParam event, BindingResult bindingResult) {

        LOGGER.info("{}", JsonUtil.objectToJson(event));
        return new CommonResult().success(null);
    }

    @ApiOperation(value = "获取日志")
    @PostMapping("/event.gif")
    public CommonResult getEvent(HttpServletResponse response,
                                 @RequestBody EventParam event,
                                 @CookieValue(value="tracker-uuid",required=false) String uuid,
                                 BindingResult bindingResult) {
        if(uuid==null){
            Customer customer=new Customer(event.getUa(),event.getUid());
            int count = customerService.addCustomer(customer);
            if (count <= 0) {
                return new CommonResult().failed();
            }
            event.setUuid(customer.getUuid());
            Cookie cookie=new Cookie("tracker-uuid",customer.getUuid());
            response.addCookie(cookie);
        }else{
            event.setUuid(uuid);
        }




        LOGGER.info("{}", JsonUtil.objectToJson(event));
        return new CommonResult().success(null);
    }

}
