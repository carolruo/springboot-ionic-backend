package com.carolruo.projeto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class StoreOrder implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "storeOrder") //Evitar erro de entidade transiente
    private Payment payment;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "delivery_address") //dando noma a chave estrangeira
    private Address address;
    @OneToMany(mappedBy = "id.storeOrder")
    private Set<ItemOrder> itemOrders = new HashSet<>();

    public StoreOrder() {
    }

    public StoreOrder(Integer id, Date instant, Customer customer, Address address) {
        this.id = id;
        this.instant = instant;
        this.customer = customer;
        this.address = address;
    }

    public double getTotalBill() {
        double sum = 0.0;
        for(ItemOrder io : itemOrders) {
            sum += io.getSubTotal();
        }
        return sum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    public void setItemOrders(Set<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreOrder that = (StoreOrder) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
