package com.hans.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hans.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;


/**
 * 友链(Link)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-11 08:16:40
 */
@Mapper
public interface LinkDao extends BaseMapper<Link> {

}

