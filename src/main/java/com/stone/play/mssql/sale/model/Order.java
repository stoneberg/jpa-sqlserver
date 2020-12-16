package com.stone.play.mssql.sale.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString(exclude = {"customer", "store", "staff", "orderItems"})
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders", schema = "sales")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //  Order status: 1 = Pending; 2 = Processing; 3 = Rejected; 4 = Completed
    @Column(nullable = false, length = 1)
    private Integer orderStatus;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private LocalDate requiredDate;

    @Column(nullable = false)
    private LocalDate shippedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_orders_customer_id"))
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", foreignKey = @ForeignKey(name = "fk_orders_store_id"))
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", foreignKey = @ForeignKey(name = "fk_orders_staff_id"))
    private Staff staff;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderItem> orderItems = new ArrayList<>();

    // utility method
    public void setCustomer(Customer customer) {
        this.customer = customer;
        if (!customer.getOrders().contains(this)) {
            this.customer.getOrders().add(this);
        }
    }

    // utility method
    public void setStore(Store store) {
        this.store = store;
        if (!store.getOrders().contains(this)) {
            this.store.getOrders().add(this);
        }
    }

    // utility method
    public void setStaff(Staff staff) {
        this.staff = staff;
        if (!staff.getOrders().contains(this)) {
            this.staff.getOrders().add(this);
        }
    }

    // utility method
    public void addOrderItem(OrderItem orderItem) {
        if (!this.orderItems.contains(orderItem)) {
            this.orderItems.add(orderItem);
        }
        orderItem.setOrder(this);
    }

}
