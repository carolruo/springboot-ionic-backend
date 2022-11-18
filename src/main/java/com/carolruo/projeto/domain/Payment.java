package com.carolruo.projeto.domain;

import com.carolruo.projeto.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    private Integer id;
    private Integer status;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id") //Coluna correspondente ao id do pedido
    @MapsId //Garante que o id do pagamento seja o mesmo do pedido
    private StoreOrder storeOrder;

    public Payment() {
    }

    public Payment(Integer id, PaymentStatus status, StoreOrder storeOrder) {
        this.id = id;
        this.status = (status==null) ? null : status.getId();
        this.storeOrder = storeOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentStatus getStatus() {
        return PaymentStatus.toEnum(status);
    }

    public void setStatus(PaymentStatus status) {
        this.status = status.getId();
    }

    public StoreOrder getStoreOrder() {
        return storeOrder;
    }

    public void setStoreOrder(StoreOrder storeOrder) {
        this.storeOrder = storeOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id.equals(payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
