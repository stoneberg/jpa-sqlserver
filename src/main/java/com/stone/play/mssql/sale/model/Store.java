package com.stone.play.mssql.sale.model;

import com.stone.play.mssql.product.model.Stock;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString(exclude = {"stocks", "staffs", "orders"})
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products", schema = "sales")
public class Store {

    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String storeName;

    @Column(length = 25)
    private String phone;

    private String email;

    private String street;

    private String city;

    @Column(length = 10)
    private String state;

    @Column(length = 5)
    private String zipCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Stock> stocks = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Staff> staffs = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Order> orders = new ArrayList<>();

    // utility method
    public void addStock(Stock stock) {
        if (!this.stocks.contains(stock)) {
            this.stocks.add(stock);
        }
        stock.setStore(this);
    }

    // utility method
    public void addStaff(Staff staff) {
        if (!this.staffs.contains(staff)) {
            this.staffs.add(staff);
        }
        staff.setStore(this);
    }

}
