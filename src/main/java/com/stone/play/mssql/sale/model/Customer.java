package com.stone.play.mssql.sale.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString(exclude = "orders")
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers", schema = "sales")
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(length = 25)
    private String phone;

    @Column(nullable = false)
    private String email;

    private String street;

    private String city;

    @Column(length = 10)
    private String state;

    @Column(length = 5)
    private String zipCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private final List<Order> orders = new ArrayList<>();

    // utility method
    public void addOrder(Order order) {
        if (!this.orders.contains(order)) {
            this.orders.add(order);
        }
        order.setCustomer(this);
    }

}
