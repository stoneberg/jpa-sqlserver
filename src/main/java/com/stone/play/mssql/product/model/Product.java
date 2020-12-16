package com.stone.play.mssql.product.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString(exclude = {"stocks", "category", "brand"})
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products", schema = "production")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer modelYear;

    @Column(precision = 10, scale = 2, nullable = false)
    private Float listPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Stock> stocks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_products_category_id"))
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name = "fk_products_brand_id"))
    private Brand brand;

    // utility method
    public void setCategory(Category category) {
        this.category = category;
        if (!category.getProducts().contains(this)) {
            this.category.getProducts().add(this);
        }
    }

    // utility method
    public void setBrand(Brand brand) {
        this.brand = brand;
        if (!brand.getProducts().contains(this)) {
            this.brand.getProducts().add(this);
        }
    }

    // utility method
    public void addStock(Stock stock) {
        if (!this.stocks.contains(stock)) {
            this.stocks.add(stock);
        }
        stock.setProduct(this);
    }

}
