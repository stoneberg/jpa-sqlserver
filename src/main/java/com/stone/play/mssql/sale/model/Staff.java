package com.stone.play.mssql.sale.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@ToString(exclude = {"parent", "child", "store", "orders"})
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staffs", schema = "sales")
public class Staff {

    @Id
    @Column(name = "staff_id")
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

    @Column(nullable = false, length = 1)
    private Integer active;

    // self join : parent
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName = "staff_id")
    private Staff parent;

    // self join : children
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private final Set<Staff> child = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", foreignKey = @ForeignKey(name = "fk_staffs_store_id"))
    private Store store;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff", cascade = CascadeType.ALL)
    private final List<Order> orders = new ArrayList<>();

    // utility method
    public void setStore(Store store) {
        this.store = store;
        if (!store.getStaffs().contains(this)) {
            this.store.getStaffs().add(this);
        }
    }

    // utility method
    public void addOrder(Order order) {
        if (!this.orders.contains(order)) {
            this.orders.add(order);
        }
        order.setStaff(this);
    }

}
