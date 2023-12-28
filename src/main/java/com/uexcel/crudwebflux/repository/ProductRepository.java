package com.uexcel.crudwebflux.repository;

import com.uexcel.crudwebflux.dto.ProductDto;
import com.uexcel.crudwebflux.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

    Flux<ProductDto> findByPriceBetween(Mono<Double> minPrice, double maxPrice);
}
