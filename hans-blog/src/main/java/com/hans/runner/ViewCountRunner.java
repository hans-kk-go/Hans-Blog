package com.hans.runner;


import com.hans.dao.ArticleDao;
import com.hans.domain.entity.Article;
import com.hans.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
//        List<Article> articles = articleDao.selectList(null);
//        Map<String, Integer> collect = articles.stream().collect(Collectors.toMap(article -> article.getId().toString(), article -> {
//            return article.getViewCount().intValue();
//        }));
//
//        redisCache.setCacheMap("ViewCount",collect);

    }
}
