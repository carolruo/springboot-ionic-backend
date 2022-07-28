package com.carolruo.projeto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal price;
    //    @JsonIgnore
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "PRODUCT_CATEGORY", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    //nome tabela, nome foreign key product, nome foreign key category; essa é a tabela que vai fazer meio de campo na associacao product-category
    private List<Category> categories = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "id.product")
    private Set<ItemOrder> itemOrders = new HashSet<>();

    public Product() {
    }

    public Product(Integer id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @JsonIgnore
    public List<StoreOrder> getStoreOrders() {
        List<StoreOrder> list = new ArrayList<>();
        for (ItemOrder x : itemOrders) {
            list.add(x.getStoreOrder());
        }
        return list;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
