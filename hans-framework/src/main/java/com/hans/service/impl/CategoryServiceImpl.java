package com.hans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hans.constants.SystemConstant;
import com.hans.dao.CategoryDao;
import com.hans.domain.entity.Article;
import com.hans.domain.entity.Category;
import com.hans.domain.vo.CategoryVo;
import com.hans.response.ResponseResult;
import com.hans.service.ArticleService;
import com.hans.service.CategoryService;
import com.hans.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-03-11 09:04:24
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Article> articles = articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstant.ARTICLES_STATUS_NORMAL);
        List<Article> list1 = articleService.list(articles);

        //获取文章的分类id，并且去重
        List<Long> categoryIds = list1.stream()
                .map(article -> article.getCategoryId())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(categoryIds);

        //查询分类表
        List<Category> categories1 = listByIds(categoryIds);

        List<Category> collect = categories1.stream()
                .filter(category -> SystemConstant.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        List<CategoryVo> categoryVos = BeanCopyUtil.copyBeanList(collect, CategoryVo.class);


        return ResponseResult.okResult(categoryVos);
    }
}
