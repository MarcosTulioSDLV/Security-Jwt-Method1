package com.springboot.securityjwtmethod1.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "TB_PRODUCT")
@AllArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String productCode;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private String productSection;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private String country;


    public Product(){
    }


}
