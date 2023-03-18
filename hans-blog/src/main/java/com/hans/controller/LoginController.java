package com.hans.controller;


import com.hans.domain.entity.User;
import com.hans.enums.AppHttpCodeEnum;
import com.hans.exception.SystemException;
import com.hans.response.ResponseResult;
import com.hans.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private BlogUserService userService;
    @PostMapping("/login")
    public ResponseResult blogLogin(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return userService.blogLogin(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return userService.blogLogout();
    }
}
