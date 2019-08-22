package com.test.it.mapstruct.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2019-08-22 09:17
 * @Description:
 */
@Data
public class ProductSkuInfo {
    private Long skuId;
    private String sku;
    private Map<String, String> attributes;
    private Date createTime;
}
