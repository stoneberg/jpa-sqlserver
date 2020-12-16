package com.stone.play.mssql.product.service;

import com.stone.play.mssql.product.dto.Product2Dto;
import com.stone.play.mssql.product.dto.ProductDto;
import com.stone.play.mssql.product.dto.ProductRes;
import com.stone.play.mssql.product.mapper.ProductMapper;
import com.stone.play.mssql.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductRes.ProductDto> getProducts() {
        return productMapper.toProductList(productRepository.findAll());
    }

    public List<ProductDto> getProductsByCategoryNameAndBrandName(String categoryName, String brandName) {
        return productRepository.findProductsByCategoryAndBrand(categoryName, brandName);
    }

    public List<Product2Dto> getProductsByCategoryNameAndBrandName2(String categoryName, String brandName) {
        return productMapper.toList(productRepository.findProductsByCategoryAndBrand(categoryName, brandName));
    }

}
