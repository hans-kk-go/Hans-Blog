package com.hans.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hans.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-11 11:48:56
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}

