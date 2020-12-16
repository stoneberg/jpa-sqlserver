package com.stone.play.mssql.product.dto;

import lombok.Data;

public class ProductRes {

    @Data
    public static class CategoryDto {
        private Integer categoryId;
        private String categoryName;
    }

    @Data
    public static class BrandDto {
        private Integer brandId;
        private String brandName;
    }

    @Data
    public static class ProductDto {
        private Integer productId;
        private String productName;
        private Integer modelYear;
        private Float listPrice;
        private CategoryDto category;
        private BrandDto brand;
    }

}
