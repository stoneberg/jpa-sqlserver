package com.stone.play.mssql.custom.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;

@Getter
@Setter
@MappedSuperclass
@SqlResultSetMapping(name = "CustomDto", classes = @ConstructorResult(targetClass = CustomDto.class,
        columns = {
                @ColumnResult(name = "productId", type = Integer.class),
                @ColumnResult(name = "categoryName", type = String.class),
                @ColumnResult(name = "brandName", type = String.class),
                @ColumnResult(name = "productName", type = String.class),
                @ColumnResult(name = "modelYear", type = Integer.class),
                @ColumnResult(name = "listPrice", type = Float.class)
        }))
public class CustomDto {

    private Integer productId;

    private String categoryName;

    private String brandName;

    private String productName;

    private Integer modelYear;

    private Float listPrice;

    // Note: The constructor in this class should match exactly with the order and
    // datatype of the columns defined in the @SqlResultSetMapping
    public CustomDto(Integer productId, String categoryName, String brandName, String productName, Integer modelYear, Float listPrice) {
        this.productId = productId;
        this.categoryName = categoryName;
        this.brandName = brandName;
        this.productName = productName;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }

}
