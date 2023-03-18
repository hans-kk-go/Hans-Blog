package com.hans.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hans.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;


/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-11 09:04:22
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {

}

