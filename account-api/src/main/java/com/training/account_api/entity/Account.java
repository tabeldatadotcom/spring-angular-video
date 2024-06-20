package com.training.account_api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "acount")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String city;

    @Column(length=10485760)
    private String imageUrl;

}
