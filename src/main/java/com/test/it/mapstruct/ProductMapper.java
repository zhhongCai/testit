package com.test.it.mapstruct;

import com.test.it.mapstruct.dto.ProductInfo;
import com.test.it.mapstruct.dto.ProductSkuInfo;
import com.test.it.mapstruct.model.Product;
import com.test.it.mapstruct.model.ProductSku;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Author: theonecai
 * @Date: Create in 2019-08-22 13:39
 * @Description:
 */
//使用spring时，注解配置
//@Mapper(componentModel = "productMapper")

@Mapper
public interface ProductMapper {
    public ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "id", target = "productId")
    public ProductInfo productToProductInfo(Product product);

    @Mapping(source = "id", target = "skuId")
    public ProductSkuInfo productSkuToProductSkuInfo(ProductSku sku);
}
