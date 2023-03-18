package com.hans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hans.constants.SystemConstant;
import com.hans.dao.CommentDao;
import com.hans.domain.entity.Comment;
import com.hans.domain.vo.CommentVo;
import com.hans.domain.vo.PageVo;
import com.hans.enums.AppHttpCodeEnum;
import com.hans.exception.SystemException;
import com.hans.response.ResponseResult;
import com.hans.service.BlogUserService;
import com.hans.service.CommentService;
import com.hans.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-03-11 20:32:50
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {

    @Autowired
    private BlogUserService blogUserService;


    @Override
    public ResponseResult getRootCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章根评论

        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //对articleId进行判断
        commentLambdaQueryWrapper.eq(SystemConstant.comment_type_comnent.equals(commentType),Comment::getArticleId, articleId);

        //评论类型
        commentLambdaQueryWrapper.eq(Comment::getType,commentType);

        //根评论rootid为-1
        commentLambdaQueryWrapper.eq(Comment::getRootId, SystemConstant.ROOT_COMMENT);

        //分页查询
        Page<Comment> commentPage = new Page<>(pageNum, pageSize);
        page(commentPage,commentLambdaQueryWrapper);

        List<Comment> rootComments = commentPage.getRecords();
        List<CommentVo> commentVoList = toCommentVoList(rootComments);
        for (CommentVo c :
                commentVoList) {
            List<CommentVo> children = getChildren(c.getId());
            c.setChildren(children);
        }

        PageVo pageVo = new PageVo(commentVoList, commentPage.getTotal());
        return ResponseResult.okResult(pageVo);

    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.COMMENT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    private List<CommentVo> getChildren(Long id){
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByAsc(Comment::getCreateTime);

        List<Comment> comments = list(queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(comments);
        return commentVoList;
    }

    private List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVoList = BeanCopyUtil.copyBeanList(list, CommentVo.class);

        //遍历vo集合
        for (CommentVo c :
                commentVoList) {
            //通过creatBy查询用户的昵称并赋值
            String nickName = blogUserService.getById(c.getCreateBy()).getNickName();
            c.setUsername(nickName);
            //通过toCommentUserId查询用户昵称并赋值
            //如果toComentUserId不为-1才进行
            if(c.getToCommentUserId()!=-1){
                String userName = blogUserService.getById(c.getToCommentUserId()).getUserName();
                c.setToCommentUserName(userName);
            }

        }
        return commentVoList;


    }


}
