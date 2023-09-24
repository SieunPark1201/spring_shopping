package com.example.spring_shopping.member.domain;

import lombok.Setter;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(length=50)
    private String name;
    @Setter
    @Column(length=50, unique = true)
    private String email;

    @Setter
    @Column(length=255)
    private String password;
}
