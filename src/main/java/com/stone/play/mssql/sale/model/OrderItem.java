package com.stone.play.mssql.sale.model;

import com.stone.play.mssql.product.model.Product;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@ToString(exclude = {"order", "product"})
@EqualsAndHashCode(callSuper = false, of = {"orderId", "itemId"})
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderItemPk.class)
@Entity
@Table(name = "order_items", schema = "sales")
public class OrderItem {

    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Id
    @Column(name = "item_id")
    private Integer itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_order_items_order_id"))
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_order_items_product_id"))
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(precision = 10, scale = 2, nullable = false)
    private Float listPrice;

    @Column(columnDefinition = "DECIMAL (4, 2) NOT NULL DEFAULT 0")
    private Float discount;

    // utility method
    public void setOrder(Order order) {
        this.order = order;
        if (!order.getOrderItems().contains(this)) {
            this.order.getOrderItems().add(this);
        }
    }

}
