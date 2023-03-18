package com.hans.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hans.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;


/**
 * 评论表(Comment)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-11 20:32:47
 */
@Mapper
public interface CommentDao extends BaseMapper<Comment> {

}

