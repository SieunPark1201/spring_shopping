package com.example.spring_shopping.orders;

import com.example.spring_shopping.items.ItemService;
import com.example.spring_shopping.member.MemberService;
import com.example.spring_shopping.orderDetail.OrderDetail;
import com.example.spring_shopping.orderDetail.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    //에러 발생시 rollback  Checked Exception에도 예외 발생을 위해서는 rollbackOn = {Exception.class}

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    ItemService itemService;

    @Autowired
    OrderDetailRepository orderDetailRepository;


//    create
    public void createOrder(OrderDto orderDto) throws Exception{
        Orders order1 = Orders.builder()
                .member(memberService.memberFindOne(Long.parseLong(orderDto.getMemberId())))
                .build();

        orderRepository.save(order1);

        for(int i = 0;i<orderDto.getCount().size();i++){
            // order_item 저장
            OrderDetail orderItem = OrderDetail.builder()
                    // order객체는 현재로서는 findById할 수 있는 매개변수가 없다.
                    // 그래서, 위에서 생성한 order객체를 orderItem에 바로 insert시킬 수 있다.(아직 DB에 저장이 되지 않았음에도
                    // 불구하고 임시저장되어 있는 상태로도 insert가 가능
                    .orders(order1)
                    .orderPrice(itemService.itemsFindOne(Long.parseLong(orderDto.getItemId().get(i))).getPrice())
                    .count(orderDto.getCount().get(i))
                    .item(itemService.itemsFindOne(Long.parseLong(orderDto.getItemId().get(i))))
                    .build();
            orderDetailRepository.save(orderItem);
        }
    }


//    find all
    public List<Orders> ordersFindAll(){
        return orderRepository.findAll();
    }


//    find one
    public Orders orderFindOne(Long id){
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


//    update
    public void orderUpdate(Long id) {
        // 주문의 상태를 order → canceled로 바꿈
        Orders customerOrder = this.orderFindOne(id);
        customerOrder.statusChange();

        // 아이템 재고량 + 시킴
        List<OrderDetail> orderItems = orderDetailRepository.findByOrders(id);
        for (OrderDetail a : orderItems) {
            a.getItem().addQuantity(a.getCount());
            orderDetailRepository.save(a);
        }
    }


//    delete
    public void OrderDelete(Long id){
            orderRepository.delete(this.orderFindOne(id));
        }

//    검색 기능

    public List<Orders> ordersSearch(OrderSearch orderSearch){

        List<Orders> orders = new ArrayList<>();

        if(isNullOrEmpty(orderSearch.getMemberName()) && orderSearch.getOrderStatus() == null){
            orders = this.ordersFindAll();

        } else if(isNullOrEmpty(orderSearch.getMemberName()) && orderSearch.getOrderStatus() != null){
            for(Orders a : this.ordersFindAll()){
                if(a.getStatus() == orderSearch.getOrderStatus()){
                    orders.add(a);
                }
            }

        } else if(!isNullOrEmpty(orderSearch.getMemberName()) && orderSearch.getOrderStatus() == null){
            for(Orders a : this.ordersFindAll()){
                if(a.getMember().getName().equals(orderSearch.getMemberName())){
                    orders.add(a);
                }
            }
        }

        else{    // 두개 이상의 컬럼으로 where 조건문을 걸떄는 and 포함
            for(Orders a : this.ordersFindAll()){
                if(a.getMember().getName().equals(orderSearch.getMemberName()) && a.getStatus()==orderSearch.getOrderStatus()){
                    orders.add(a);
                }
            }
        }
        return orders;
    }

    private boolean isNullOrEmpty(String str){
        if(str == null){
            return true;
        }else if(str != null && str.isEmpty()){
            return true;
        }else{
            return false;
        }
    }


}
