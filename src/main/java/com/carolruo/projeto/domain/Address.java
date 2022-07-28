package com.carolruo.projeto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Address implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String number;
    private String addressContinued;
    private String neighborhood;
    private String zipCode;
    //O endereço tem a chave estrangeira para cidade e para cliente: (a tabela dele tem uma coluna para cada um deles)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id") //nome da coluna que vai corresponder ao id do cliente de x endereço
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CustomerCity customerCity;

    public Address() {
    }

    public Address(Integer id, String street, String number, String addressContinued, String neighborhood, String zipCode, Customer customer, CustomerCity customerCity) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.addressContinued = addressContinued;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.customer = customer;
        this.customerCity = customerCity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddressContinued() {
        return addressContinued;
    }

    public void setAddressContinued(String addressContinued) {
        this.addressContinued = addressContinued;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerCity getCity() {
        return customerCity;
    }

    public void setCity(CustomerCity customerCity) {
        this.customerCity = customerCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
