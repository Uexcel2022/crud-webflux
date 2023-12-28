package com.uexcel.crudwebflux.utils;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.uexcel.crudwebflux.dto.ProductDto;
import com.uexcel.crudwebflux.entity.Product;
import org.springframework.beans.BeanUtils;

public class AppUtil {

    public static ProductDto entityToDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        return  productDto;

    }

    public static Product DtoToEntity(ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        return  product;

    }
}
