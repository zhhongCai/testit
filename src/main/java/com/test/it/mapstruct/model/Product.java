package com.test.it.mapstruct.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: theonecai
 * @Date: Create in 2019-08-22 09:14
 * @Description:
 */
@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private List<ProductSku> skus;
    private Date createTime;
}
