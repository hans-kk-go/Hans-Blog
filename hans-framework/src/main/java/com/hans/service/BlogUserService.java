package com.hans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hans.domain.entity.User;
import com.hans.response.ResponseResult;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-03-11 11:48:58
 */
public interface BlogUserService extends IService<User> {

    ResponseResult blogLogin(User user);

    ResponseResult blogLogout();

    ResponseResult userInfo();

    ResponseResult register(User user);
}

