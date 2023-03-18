package com.hans.controller;


import com.hans.constants.SystemConstant;
import com.hans.domain.dto.AddComment;
import com.hans.domain.entity.Comment;
import com.hans.response.ResponseResult;
import com.hans.service.CommentService;
import com.hans.utils.BeanCopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论",description = "评论相关接口")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult getRootCommentList(Long articleId, Integer pageNum,Integer pageSize){
        return commentService.getRootCommentList(SystemConstant.comment_type_comnent,articleId, pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody AddComment addComment){
        Comment comment = BeanCopyUtil.copyBean(addComment, Comment.class);
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表",notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页号"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小")
    })
    public ResponseResult linkCommentList( Integer pageNum,Integer pageSize){
        return commentService.getRootCommentList(SystemConstant.comment_type_link, null, pageNum,pageSize);
    }

}
