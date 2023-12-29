package com.uexcel.crudwebflux;

import com.uexcel.crudwebflux.controller.ProductController;
import com.uexcel.crudwebflux.dto.ProductDto;
import com.uexcel.crudwebflux.service.ProductService;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.awaitility.Awaitility.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class CrudWebfluxApplicationTests {
    @Autowired
    private  WebTestClient webTestClient;
    @MockBean
    private ProductService productService;

    @Test
    public void addProductTest(){
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto(1L,"Apple",5,1500));
                when(productService.saveProduct(productDtoMono)).thenReturn(productDtoMono);

                webTestClient.post().uri("/product").body(productDtoMono, ProductDto.class)
                        .exchange()
                        .expectStatus().isOk();
    }

    @Test
    public void getAllProduct(){
        Flux<ProductDto> products = Flux.just(new ProductDto(1L,"Apple",5,1500),
                new ProductDto(2L,"HP",4,1350));
        when(productService.getAllProduct()).thenReturn(products);

        Flux<ProductDto> responseBody =  webTestClient.get().uri("/product")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new ProductDto(1L,"Apple",5,1500))
                .expectNext(new ProductDto(2L,"HP",4,1350))
                .verifyComplete();

    }

    @Test
    public  void getProductById(){
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto(1L,"Apple",5,1500));
        when(productService.getProductById(any())).thenReturn(productDtoMono);

        Flux<ProductDto> responseBody =  webTestClient.get().uri("/product/1")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(obj->obj.getName().equals("Apple"))
                .verifyComplete();
    }

    @Test
    public void updateProduct(){
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto(1L,"Apple",5,1500));
        when(productService.updateProduct(productDtoMono,1L)).thenReturn(productDtoMono);

      webTestClient.put().uri("/product/update/1")
              .body(Mono.just(productDtoMono),ProductDto.class)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void deleteProductTest(){
        when(productService.deleteProduct(any())).thenReturn(Mono.empty());
        webTestClient.delete().uri("/product/delete/1")
                .exchange()
                .expectStatus().isOk();
    }


}
