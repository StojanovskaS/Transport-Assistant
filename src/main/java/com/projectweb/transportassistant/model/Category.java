package com.projectweb.transportassistant.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "mk_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String categoryName;
    String grad;
    public Category(String categoryName, String grad) {
        this.categoryName = categoryName;
        this.grad = grad;
    }
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category() {
    }
}
