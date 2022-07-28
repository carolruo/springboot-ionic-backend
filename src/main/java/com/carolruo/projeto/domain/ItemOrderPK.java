package com.carolruo.projeto.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemOrderPK implements Serializable {
    private static final long serialVersionUID = 1l;

    @ManyToOne
    @JoinColumn(name = "storeOrder_id")
    private StoreOrder storeOrder;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public StoreOrder getStoreOrder() {
        return storeOrder;
    }

    public void setStoreOrder(StoreOrder storeOrder) {
        this.storeOrder = storeOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrderPK that = (ItemOrderPK) o;
        return storeOrder.equals(that.storeOrder) && product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeOrder, product);
    }
}
