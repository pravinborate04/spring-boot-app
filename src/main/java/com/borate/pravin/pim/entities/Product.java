package com.borate.pravin.pim.entities;

import com.borate.pravin.pim.helper.Constants;

import javax.persistence.*;
import java.util.List;

/**
 * @author Pravin Borate
 * 11/03/21
 */
@Entity
@Table(name = Constants.PRODUCT)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "brand")
    private String brand;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Document> images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Document> getImages() {
        return images;
    }

    public void setImages(List<Document> images) {
        this.images = images;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
