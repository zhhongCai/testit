package com.test.it.mapstruct.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2019-08-22 09:17
 * @Description:
 */
@Data
public class ProductInfo {
    private Long productId;
    private String name;
    private String description;
    private List<ProductSkuInfo> skus;
    private Date createTime;
}
