package com.hans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hans.domain.entity.Link;
import com.hans.response.ResponseResult;

/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-03-11 08:16:41
 */
public interface LinkService extends IService<Link> {

    ResponseResult getLinkList();
}

