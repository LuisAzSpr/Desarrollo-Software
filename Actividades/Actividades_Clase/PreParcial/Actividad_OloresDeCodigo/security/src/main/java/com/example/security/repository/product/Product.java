package com.example.security.repository.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(unique = true,name="productname")
    private String productname;

    @Column(name="description")
    private String description;

    @Column(name="url")
    private String url;

    @Column(name="price")
    @NotNull
    @Positive
    private float price;

    @Column(name="number")
    @NotNull
    @Positive
    private int number;

    @Column(name="type")
    private Type typeOfProduct;
}
