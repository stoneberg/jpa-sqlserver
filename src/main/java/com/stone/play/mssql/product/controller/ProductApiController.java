package com.stone.play.mssql.product.controller;

import com.stone.play.common.response.ResponseDto;
import com.stone.play.mssql.product.dto.Product2Dto;
import com.stone.play.mssql.product.dto.ProductDto;
import com.stone.play.mssql.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductApiController {

    private final ProductService productService;

    @GetMapping("test")
    public ResponseDto<?> products() {
        return ResponseDto.of(productService.getProducts());
    }

    @GetMapping("test2/categoryname/{categoryName}/brandname/{brandName}")
    public ResponseDto<?> productsByKeyword(@PathVariable final String categoryName, @PathVariable final String brandName) {
        List<ProductDto> products = productService.getProductsByCategoryNameAndBrandName(categoryName, brandName);
        products.forEach(productDto -> log.info("@product=====>{}", productDto.getProductId()));
        return ResponseDto.of(productService.getProductsByCategoryNameAndBrandName(categoryName, brandName));
    }

    @GetMapping("test3/categoryname/{categoryName}/brandname/{brandName}")
    public ResponseDto<?> productsByKeyword2(@PathVariable final String categoryName, @PathVariable final String brandName) {
        List<Product2Dto> products = productService.getProductsByCategoryNameAndBrandName2(categoryName, brandName);
        products.forEach(productDto -> log.info("@product2=====>{}", productDto.getProductId()));
        return ResponseDto.of(productService.getProductsByCategoryNameAndBrandName2(categoryName, brandName));
    }

}
