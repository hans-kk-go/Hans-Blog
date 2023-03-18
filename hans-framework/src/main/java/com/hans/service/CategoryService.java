package com.hans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hans.domain.entity.Category;
import com.hans.response.ResponseResult;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-03-11 09:04:23
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

}

