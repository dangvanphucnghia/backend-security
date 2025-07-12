package com.phucnghia.backend_sercurity.model;

import jakarta.persistence.*;
import lombok.*;
import org.aspectj.lang.annotation.control.CodeGenerationHint;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_address")
public class Address extends AbstractEntity<Long>{
    @Column(name = "apartment_number")
    private String apartmentNumber;

    @Column(name ="floor")
    private String floor;

    @Column(name = "building")
    private String building;

    @Column(name = "street_number")
    private String street;

    @Column(name ="city")
    private String city;

    @Column(name = "country")
    private  String country;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
    private User user;

    @Column(name ="address_type")
    private String addressType;
}
