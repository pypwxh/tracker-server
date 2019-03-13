package com.yfyld.tracker.controller;

import com.yfyld.tracker.util.CommonResult;
import com.yfyld.tracker.dto.UserLoginParam;
import com.yfyld.tracker.model.User;
import com.yfyld.tracker.service.user.UserService;
import com.yfyld.tracker.util.ValidateGroups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@Api(description = "账号相关")
@RestController
@RequestMapping(value = "/user")
public class UserConstroller {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserConstroller.class);


    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private HttpServletRequest request;




    @ApiOperation(value = "注册", notes = "注册账号")
    @PostMapping("/signup")
    public CommonResult addUser(@Validated(ValidateGroups.Add.class) @RequestBody UserLoginParam user, BindingResult bindingResult) {
        int count = userService.addUser(new User(user.getUsername(),bCryptPasswordEncoder.encode(user.getPassword())));
        if (count > 0) {
            return new CommonResult().success(null);
        } else {
            return new CommonResult().failed();
        }
    }


    @ApiOperation(value = "登录", notes = "登录账号")
    @PostMapping("/login")
    public CommonResult login(@Validated @RequestBody UserLoginParam user, BindingResult bindingResult) {

        Boolean isRememberMe=user.getIsRememberMe();
        isRememberMe=isRememberMe==null?false:isRememberMe;
        String token = userService.login(user.getUsername(),user.getPassword(),isRememberMe);
        if(token==null){
            return new CommonResult().validateFailed("账号或者密码错误");
        }
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
       return new CommonResult().success(tokenMap);
    }



    @GetMapping("/info")
    @ApiOperation(value = "用户详情")
    public CommonResult getUserInfo(Principal principal) {
        String  username = principal.getName();
        return new CommonResult().success(userService.getUserInfo(username));
    }


    @PreAuthorize("hasAuthority('USER3')")
    @GetMapping("/")
    @ApiOperation(value = "用户列表")
    public CommonResult getUserList() {
        return new CommonResult().pageSuccess(userService.getUserList());
    }
}
