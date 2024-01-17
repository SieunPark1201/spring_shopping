package com.example.spring_shopping.orders;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private List<Long> count;
    private List<String> itemId;
    private String status;
    private String memberId;


}