package com.carolruo.projeto.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class CustomerCity implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private CustomerState customerState;

    public CustomerCity() {
    }

    public CustomerCity(Integer id, String name, CustomerState customerState) {
        this.id = id;
        this.name = name;
        this.customerState = customerState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerState getState() {
        return customerState;
    }

    public void setState(CustomerState customerState) {
        this.customerState = customerState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerCity customerCity = (CustomerCity) o;
        return id.equals(customerCity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
