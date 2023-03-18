package com.hans.constants;

/**
 * @author Yozuru
 */

public class SystemConstant {
    /**
     * 表示文章为已发布状态
     */
    public static final Integer ARTICLES_STATUS_NORMAL = 0;
    /**
     * 表示文章为编辑状态
     */
    public static final Integer ARTICLES_STATUS_DRAFT = 1;
    /**
     * 表示热门文章的展示条数
     */
    public static final Integer HOT_ARTICLES_LIST_SIZE = 10;
    /**
     * 表示分组为正常状态
     */
    public static final String STATUS_NORMAL = "0";
    /**
     * 表示分组为禁用状态
     */
    public static final Integer STATUS_FORBIDDEN = 1;
    /**
     * 表示redisKey
     */
    public static final String REDIS_KEY = "blogkey:";
    /**
     * 表示redisKey
     */
    public static final Integer ROOT_COMMENT = -1;
    public static final String comment_type_link = "1";
    public static final String comment_type_comnent = "0";







}
