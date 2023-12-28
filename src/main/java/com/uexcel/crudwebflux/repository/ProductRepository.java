package com.uexcel.crudwebflux.repository;

import com.uexcel.crudwebflux.dto.ProductDto;
import com.uexcel.crudwebflux.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

    @Query ("SELECT * FROM product  WHERE price BETWEEN  :minprice AND :maxPrice")
    Flux<ProductDto> findByPriceBetween(@Param("minPrice") double minPrice,@Param("maxPrice") double maxPrice);

}
