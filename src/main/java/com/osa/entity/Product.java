package com.osa.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int productId;
    
    private String productName;
    @Column(length = 2000)
    private String productDescription;
   
    private double productDiscountedprice;
    
    private double productActualPrice;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="product_images",
    joinColumns = {
    		@JoinColumn(name="product_id")
    },
    inverseJoinColumns = {
    		@JoinColumn(name="image_id")
    }
    )
    private Set<ImageModel> productImages;



}
