package com.hans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hans.domain.entity.Article;
import com.hans.response.ResponseResult;

/**
 * 文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2023-03-10 20:21:37
 */
public interface ArticleService extends IService<Article> {

    ResponseResult GetHotArticle();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

}

