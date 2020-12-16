package com.stone.play.mssql.product.mapper;

import com.stone.play.config.MapStructMapperConfig;
import com.stone.play.mssql.product.dto.Product2Dto;
import com.stone.play.mssql.product.dto.ProductDto;
import com.stone.play.mssql.product.dto.ProductRes;
import com.stone.play.mssql.product.dto.ProductRes.BrandDto;
import com.stone.play.mssql.product.dto.ProductRes.CategoryDto;
import com.stone.play.mssql.product.model.Brand;
import com.stone.play.mssql.product.model.Category;
import com.stone.play.mssql.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructMapperConfig.class)
public interface ProductMapper {

    @Mapping(source = "id", target = "categoryId")
    CategoryDto toCategoryDto(Category category);

    @Mapping(source = "id", target = "brandId")
    BrandDto toBrandDto(Brand brand);

    @Mapping(source = "id", target = "productId")
    ProductRes.ProductDto toProductDto(Product product);
    List<ProductRes.ProductDto> toProductList(List<Product> products);

    Product2Dto toDto(ProductDto productDto);
    List<Product2Dto> toList(List<ProductDto> productDtos);

}
