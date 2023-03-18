package com.hans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hans.constants.SystemConstant;
import com.hans.dao.ArticleDao;
import com.hans.dao.CategoryDao;
import com.hans.domain.entity.Article;
import com.hans.domain.entity.Category;
import com.hans.domain.vo.ArticleDetailVo;
import com.hans.domain.vo.ArticleListVo;
import com.hans.domain.vo.HotArticlesVo;
import com.hans.domain.vo.PageVo;
import com.hans.response.ResponseResult;
import com.hans.service.ArticleService;
import com.hans.service.CategoryService;
import com.hans.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.LambdaConversionException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 20:21:37
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {
    @Autowired
    private CategoryDao categoryService;
    @Override
    public ResponseResult GetHotArticle() {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstant.ARTICLES_STATUS_NORMAL);
        articleLambdaQueryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1, 10);
        page(page,articleLambdaQueryWrapper);

        List<Article> articles = page.getRecords();

        List<HotArticlesVo> hotArticlesVos = BeanCopyUtil.copyBeanList(articles, HotArticlesVo.class);
//        System.out.println(list);

        return ResponseResult.okResult(hotArticlesVos);
    }



    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getStatus,SystemConstant.ARTICLES_STATUS_NORMAL);
        articleLambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
//        articleLambdaQueryWrapper.eq(Article::get)
        articleLambdaQueryWrapper.orderByDesc(Article::getIsTop);

        Page<Article> articlePage = new Page<>(pageNum, pageSize);
        page(articlePage, articleLambdaQueryWrapper);

        List<Article> articles= articlePage.getRecords();
//        List<Object> collect = articles.stream()
//                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
//                .collect(Collectors.toList());
//        categoryService.listByIds(collect).va
        for (Article article :
                articles) {
            Category category = categoryService.selectById(article.getCategoryId());
            String name = category.getName();
            article.setCategoryName(name);
        }

        List<ArticleListVo> articleListVos = BeanCopyUtil.copyBeanList(articles, ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos, articlePage.getTotal());
        return ResponseResult.okResult(pageVo);


    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        Article article = getById(id);

        ArticleDetailVo articleDetailVo = BeanCopyUtil.copyBean(article, ArticleDetailVo.class);

        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.selectById(categoryId);
        if (category != null){
            articleDetailVo.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(articleDetailVo);
    }

}
