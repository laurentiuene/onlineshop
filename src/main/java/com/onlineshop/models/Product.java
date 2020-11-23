package com.onlineshop.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_specifications")
    private String productSpecifications;

    @Column(name = "product_price")
    private Float productPrice;

    @Column(name = "product_image")
    private String productImage;

    @JsonManagedReference(value = "cartItem")
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;

    @JsonManagedReference(value = "orderItem")
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    @JsonManagedReference(value = "stock")
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Stock stock;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Review> reviews;
}
