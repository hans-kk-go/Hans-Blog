package com.hans.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hans.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;


/**
 * 文章表(Article)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-10 20:47:26
 */
@Mapper
public interface ArticleDao extends BaseMapper<Article> {

}

