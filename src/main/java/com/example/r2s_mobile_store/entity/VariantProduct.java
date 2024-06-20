package com.example.r2s_mobile_store.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "variant_product")
public class VariantProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "material", length = 32)
    private String material;

    @Column(name = "color", length = 32)
    private String color;

    @Column(name = "size", length = 5)
    private String size;

    @Column(name = "price")
    private float price;

    @Column(name = "quantity")
    private long quantity;
}
