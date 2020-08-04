package com.onlineshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock")
    private Integer stockId;

    private Integer quantity;

    @JsonBackReference(value = "stock")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product", referencedColumnName = "id_product")
    private Product product;
}


