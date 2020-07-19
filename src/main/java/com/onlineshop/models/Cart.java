package com.onlineshop.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart")
    private Integer cartId;

    @JsonManagedReference
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;
}
