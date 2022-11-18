package com.carolruo.projeto.domain;

import com.carolruo.projeto.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("card")
public class PaymentCard extends Payment {
    private static final long serialVersionUID = 1l;

    private Integer installments;

    public PaymentCard() {
    }

    public PaymentCard(Integer id, PaymentStatus status, StoreOrder storeOrder, Integer installments) {
        super(id, status, storeOrder);
        this.installments = installments;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
}
