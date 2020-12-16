package com.stone.play.mssql.product.repository;

import com.stone.play.mssql.product.dto.ProductDto;
import com.stone.play.mssql.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "{call production.getProductsByCategoryAndBrand(:categoryName, :brandName)}", nativeQuery = true)
    List<ProductDto> findProductsByCategoryAndBrand(@Param("categoryName") String categoryName, @Param("brandName") String brandName);

}
