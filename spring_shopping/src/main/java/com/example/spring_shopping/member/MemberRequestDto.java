package com.example.spring_shopping.member;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberRequestDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String city;
    private String street;
    private int zipcode;

    private LocalDateTime createDate;

}
