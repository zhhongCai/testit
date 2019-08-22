package com.test.it.mapstruct.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2019-08-22 09:15
 * @Description:
 */
@Data
public class ProductSku {
    private Long id;
    private String sku;
    private Map<String, String> attributes;
    private Date createTime;
}
