package com.hans.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hans.dao.LinkDao;
import com.hans.domain.entity.Link;
import com.hans.domain.vo.LInkVo;
import com.hans.response.ResponseResult;
import com.hans.utils.BeanCopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.hans.service.LinkService;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-03-11 08:16:42
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkDao, Link> implements LinkService {

    @Override
    public ResponseResult getLinkList() {
        List<Link> list = list();
        List<LInkVo> lInkVos = BeanCopyUtil.copyBeanList(list, LInkVo.class);
        return ResponseResult.okResult(lInkVos);
    }
}
