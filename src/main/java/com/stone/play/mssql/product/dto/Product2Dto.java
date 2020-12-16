package com.stone.play.mssql.product.dto;

public class Product2Dto {

    private Integer productId;

    private String categoryName;

    private String brandName;

    private String productName;

    private Integer modelYear;

    private Float listPrice;

    public Product2Dto(Integer productId, String categoryName, String brandName, String productName, Integer modelYear, Float listPrice) {
        this.productId = productId;
        this.categoryName = categoryName;
        this.brandName = brandName;
        this.productName = productName;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public Float getListPrice() {
        return listPrice;
    }

    public void setListPrice(Float listPrice) {
        this.listPrice = listPrice;
    }
}
