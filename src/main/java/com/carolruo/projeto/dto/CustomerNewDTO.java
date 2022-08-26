package com.carolruo.projeto.dto;

import com.carolruo.projeto.services.validation.CustomerInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@CustomerInsert
public class CustomerNewDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min = 5, max = 80, message = "Tamanho deve ser entre 5 e 80 caracteres")
    private String name;
    @NotEmpty(message = "Preenchimento obrigatorio")
    @Email(message = "Email invalido")
    private String email;
    @NotEmpty(message = "Preenchimento obrigatorio")
    private String cpfOrCnpj;
    private Integer customerTypeId;
    @NotEmpty(message = "Preenchimento obrigatorio")
    private String street;
    @NotEmpty(message = "Preenchimento obrigatorio")
    private String number;
    private String addressContinued;
    private String neighborhood;
    @NotEmpty(message = "Preenchimento obrigatorio")
    private String zipCode;
    private String contactNumber1;
    private String contactNumber2;
    private String contactNumber3;
    private Integer cityId;

    public CustomerNewDTO() {}

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

    public Integer getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
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

    public String getContactNumber1() {
        return contactNumber1;
    }

    public void setContactNumber1(String contactNumber1) {
        this.contactNumber1 = contactNumber1;
    }

    public String getContactNumber2() {
        return contactNumber2;
    }

    public void setContactNumber2(String contactNumber2) {
        this.contactNumber2 = contactNumber2;
    }

    public String getContactNumber3() {
        return contactNumber3;
    }

    public void setContactNumber3(String contactNumber3) {
        this.contactNumber3 = contactNumber3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
