package com.test.it.mapstruct;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.test.it.mapstruct.dto.ProductInfo;
import com.test.it.mapstruct.model.Product;
import com.test.it.mapstruct.model.ProductSku;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2019-08-22 13:42
 * @Description:
 */
public class MapperTest {
    @Test
    public void test() {
        Date now = new Date();
        Product product = new Product();
        product.setId(1L);
        product.setCreateTime(now);
        product.setDescription("description");
        product.setName("name");

        ProductSku sku = new ProductSku();
        Map<String, String> attributes = Maps.newHashMapWithExpectedSize(2);
        attributes.put("color", "red");
        attributes.put("size", "L");
        sku.setAttributes(attributes);
        sku.setCreateTime(now);
        sku.setSku("SKU001");
        sku.setId(2L);

        List<ProductSku> skus = Lists.newArrayListWithCapacity(1);
        skus.add(sku);
        product.setSkus(skus);

        ProductInfo productInfo = ProductMapper.INSTANCE.productToProductInfo(product);
        Assert.assertNotNull(productInfo);
    }
}
