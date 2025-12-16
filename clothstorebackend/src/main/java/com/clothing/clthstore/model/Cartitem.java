package com.clothing.clthstore.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart_item")
public class Cartitem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private product product;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
