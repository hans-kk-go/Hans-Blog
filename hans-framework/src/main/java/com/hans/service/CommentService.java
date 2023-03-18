package com.hans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hans.domain.entity.Comment;
import com.hans.response.ResponseResult;

/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-03-11 20:32:49
 */
public interface CommentService extends IService<Comment> {

    ResponseResult getRootCommentList(String s, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

