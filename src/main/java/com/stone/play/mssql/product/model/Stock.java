package com.stone.play.mssql.product.model;

import com.stone.play.mssql.sale.model.Store;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@ToString(exclude = {"product", "store"})
@EqualsAndHashCode(callSuper = false, of = {"storeId", "productId"})
@NoArgsConstructor
@AllArgsConstructor
@IdClass(StockPk.class)
@Entity
@Table(name = "stocks", schema = "production")
public class Stock {

    @Id
    @Column(name = "store_id")
    private Integer storeId;

    @Id
    @Column(name = "product_id")
    private Integer productId;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_stocks_product_id"))
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_stocks_store_id"))
    private Store store;

    // utility method
    public void setProduct(Product product) {
        this.product = product;
        if (!product.getStocks().contains(this)) {
            this.product.getStocks().add(this);
        }
    }

    // utility method
    public void setStore(Store store) {
        this.store = store;
        if (!store.getStocks().contains(this)) {
            this.store.getStocks().add(this);
        }
    }

}
