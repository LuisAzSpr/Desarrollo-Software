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
public class ProductEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String productName;

    @NotNull
    @Positive
    private float price;

    @NotNull
    @Positive
    private int number;

    @NotNull
    private Type typeOfProduct;
}
