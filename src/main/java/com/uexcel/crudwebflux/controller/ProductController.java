package com.uexcel.crudwebflux.controller;

import com.uexcel.crudwebflux.dto.ProductDto;
import com.uexcel.crudwebflux.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDto){
      return   productService.saveProduct(productDto);
    }

    @GetMapping("/{id}")
    public  Mono<ProductDto> getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @GetMapping
    public Flux<ProductDto> getALLProduct(){
        return  productService.getAllProduct();
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct(
            @RequestBody Mono<ProductDto> productDtoMono , @PathVariable Long id ){
        return productService.updateProduct(productDtoMono, id);
    }

    @GetMapping("/price")
    public Flux<ProductDto> getRangeOfProductByPrice(
            @RequestParam("min") Double min, @RequestParam("max") Double max){
        return productService.getRangeByPrice(min,max);
    }

    @DeleteMapping("/delete/{id}")
    private Mono<Void> deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
}
