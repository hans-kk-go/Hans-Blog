package com.hans.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 前台展示分类的vo
 * @author Yozuru
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo implements Serializable {
    private Long id;
    private String name;
}
