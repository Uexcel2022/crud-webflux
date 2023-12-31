package com.uexcel.crudwebflux.service;

import com.uexcel.crudwebflux.dto.ProductDto;
import com.uexcel.crudwebflux.repository.ProductRepository;
import com.uexcel.crudwebflux.utils.AppUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImp implements ProductService{
    private  final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Flux<ProductDto> getAllProduct() {
        return productRepository.findAll().map(AppUtil::entityToDto);
    }

    @Override
    public Mono<ProductDto> getProductById(Long id) {
        return productRepository.findById(id).map(AppUtil::entityToDto);
    }

    @Override
    public Flux<ProductDto> getRangeByPrice(double min, double max) {
        return productRepository.findByPriceBetween(min,max);
    }

    @Override
    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDto) {
      return   productDto.map(AppUtil::DtoToEntity)
                .flatMap(productRepository::save)
                .map(AppUtil::entityToDto);
    }

    @Override
    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDto, Long id){
      return   productRepository.findById(id)
              .flatMap(p->productDto.map(AppUtil::DtoToEntity)
                .doOnNext(i->i.setId(id)))
                .flatMap(productRepository::save)
                .map(AppUtil::entityToDto);

    }

    @Override
     public  Mono<Void> deleteProduct(Long id){
       return productRepository.deleteById(id);
     }

}
