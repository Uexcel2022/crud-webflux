package com.uexcel.crudwebflux.service;

import com.uexcel.crudwebflux.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductDto> getAllProduct();
    Mono<ProductDto> getProductById(Long id);

    public Flux<ProductDto> getRangeByPrice(double min, double max);
    Mono<ProductDto> saveProduct(Mono<ProductDto> productDto);

    Mono<ProductDto> upDateProduct(Mono<ProductDto> productDto, Long id);

    Mono<Void> deleteProduct(Long id);
}
