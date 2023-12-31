package com.example.spring_shopping.orderDetail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;


import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    OrderDetailRepository orderItemRepository;


    // create
    public void orderItemSave(OrderDetailDto orderItemDto) throws Exception {

        OrderDetail orderItems = OrderDetail.builder()
                .orderPrice(orderItemDto.getItemId().getPrice())
                .count(orderItemDto.getCount())
                .item(orderItemDto.getItemId())
                .customerOrder(orderItemDto.getCustomerOrder())
                .build();
        orderItemRepository.save(orderItems);
    }


    // read_all
    public List<OrderDetail> order_find_all(){

        List<OrderDetail> orderItems = orderItemRepository.findAll();
        return orderItemRepository.findAll();
    }

    //read_one
    public OrderDetail order_find_one(Long id){
        return orderItemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
