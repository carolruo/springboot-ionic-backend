package com.carolruo.projeto.domain;

import com.carolruo.projeto.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentSlip extends Payment {
    private static final long serialVersionUID = 1l;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    public PaymentSlip() {
    }

    public PaymentSlip(Integer id, PaymentStatus status, StoreOrder storeOrder, Date dueDate, Date paymentDate) {
        super(id, status, storeOrder);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
