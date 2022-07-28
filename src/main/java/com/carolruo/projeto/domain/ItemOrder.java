package com.carolruo.projeto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemOrder implements Serializable {
    private static final long serialVersionUID = 1l;

    @JsonIgnore
    @EmbeddedId
    private ItemOrderPK id = new ItemOrderPK();
    private Double discount;
    private Integer quantity;
    private Double price;

    public ItemOrder() {
    }

    public ItemOrder(StoreOrder storeOrder, Product product, Double discount, Integer quantity, Double price) {
        id.setStoreOrder(storeOrder);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public StoreOrder getStoreOrder() {
        return this.id.getStoreOrder();
    }

    public Product getProduct() {
        return this.id.getProduct();
    }

    public ItemOrderPK getId() {
        return id;
    }

    public void setId(ItemOrderPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrder itemOrder = (ItemOrder) o;
        return id.equals(itemOrder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
