package com.carolruo.projeto.domain;

import com.carolruo.projeto.domain.enums.CustomerType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String cpfOrCnpj;
    private Integer customerTypeId;
    @OneToMany(mappedBy = "customer")
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection //Entidade fraca
    @CollectionTable(name = "TELEFONE") // Fica assim: table= TELEFONE colunas= CUSTOMER_ID e CONTACT_NUMBERS
    private Set<String> contactNumbers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<StoreOrder> storeOrders = new ArrayList<>();

    public Customer() {
    }

    public Customer(Integer id, String name, String email, String cpfOrCnpj, CustomerType customerType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.customerTypeId = customerType.getId();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public CustomerType getCustomerTypeId() {
        return CustomerType.toEnum(customerTypeId);
    }

    public void setCustomerTypeId(CustomerType customerTypeId) {
        this.customerTypeId = customerTypeId.getId();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(Set<String> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public List<StoreOrder> getStoreOrders() {
        return storeOrders;
    }

    public void setStoreOrders(List<StoreOrder> storeOrders) {
        this.storeOrders = storeOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
