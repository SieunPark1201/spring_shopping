package com.example.spring_shopping.orderDetail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;


    // create
    public void createOrderDetail(OrderDetailDto orderDetailDto) throws Exception {

        OrderDetail orderItems = OrderDetail.builder()
                .orderPrice(orderDetailDto.getItemId().getPrice())
                .count(orderDetailDto.getCount())
                .item(orderDetailDto.getItemId())
                .orders(orderDetailDto.getCustomerOrder())
                .build();
        orderDetailRepository.save(orderItems);
    }


    // read_all
    public List<OrderDetail> orderDetailFindAll(){

        List<OrderDetail> orderItems = orderDetailRepository.findAll();
        return orderDetailRepository.findAll();
    }

    //read_one
    public OrderDetail orderDetailFindOne(Long id){
        return orderDetailRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


}
