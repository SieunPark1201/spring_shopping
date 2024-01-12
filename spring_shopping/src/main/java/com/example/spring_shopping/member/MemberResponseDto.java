package com.example.spring_shopping.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberResponseDto {


    private Long id;
    private String name;
    private String email;
    private String password;
    private String city;
    private String street;
    private int zipcode;
    private LocalDateTime createDate;
    private Long orderCount;
    private Long total;

}
