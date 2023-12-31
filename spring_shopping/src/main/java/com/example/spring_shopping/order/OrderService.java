package com.example.spring_shopping.order;

import com.example.spring_shopping.items.ItemService;
import com.example.spring_shopping.member.MemberService;
import com.example.spring_shopping.orderDetail.OrderDetail;
import com.example.spring_shopping.orderDetail.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional(rollbackOn = {Exception.class})
public class OrderService {


//에러 발생시 rollback  Checked Exception에도 예외 발생을 위해서는 rollbackOn = {Exception.class} 추가 확인 필요

        @Autowired
        OrderRepository orderRepository;
        @Autowired
        MemberService memberService;
        @Autowired
        ItemService itemService;
        @Autowired
        OrderDetailRepository orderItemRepository;

        //Create
        public void order_save(OrderDto order) throws Exception {

            // ★아이템 1개에 여러개의 주문으로 저장됨 → 확인필요

            // order 저장
            Order order1 = Order.builder()
                    .member1(memberService.find_one(Long.parseLong(order.getMemberId())))
                    .build();
            orderRepository.save(order1);

            for(int i = 0;i<order.getCount().size();i++){
                // order_item 저장
                OrderDetail orderItem = OrderDetail.builder()
                        // order객체는 현재로서는 findById할 수 있는 매개변수가 없다.
                        // 그래서, 위에서 생성한 order객체를 orderItem에 바로 insert시킬 수 있다.(아직 DB에 저장이 되지 않았음에도
                        // 불구하고 임시저장되어 있는 상태로도 insert가 가능
                        .customerOrder(order1)
                        .orderPrice(itemService.item_one(Long.parseLong(order.getItemId().get(i))).getPrice())
                        .count(order.getCount().get(i))
                        .item(itemService.item_one(Long.parseLong(order.getItemId().get(i))))
                        .build();
                orderItemRepository.save(orderItem);
            }
        }

        // read_all
        public List<Order> order_find_all(){
            return orderRepository.findAll();
        }

        // read_one
        public Order order_find_one(Long id){
            return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        }

        // update_status
        public void order_change_status(Long id){
            // 주문의 상태를 order → canceled로 바꿈
            Order customerOrder = this.order_find_one(id);
            customerOrder.status_Change();

            // 아이템 재고량 + 시킴
            List<OrderDetail> orderItems = orderItemRepository.findByCustomerOrderId(id);
            for(OrderDetail a : orderItems){
                a.getItem().AddQuantity(a.getCount());
                orderItemRepository.save(a);
            }
        }

        // delete
        public void order_delete(Long id){
            orderRepository.delete(this.order_find_one(id));
        }


        // read_search
        public List<Order> order_find_search(OrderSearch orderSearch){

            List<Order> orders = new ArrayList<>();

            if(isNullOrEmpty(orderSearch.getMemberName()) && orderSearch.getOrderStatus() == null){
                orders = this.order_find_all();

            } else if(isNullOrEmpty(orderSearch.getMemberName()) && orderSearch.getOrderStatus() != null){
                for(Order a : this.order_find_all()){
                    if(a.getStatus() == orderSearch.getOrderStatus()){
                        orders.add(a);
                    }
                }

            } else if(!isNullOrEmpty(orderSearch.getMemberName()) && orderSearch.getOrderStatus() == null){
                for(Order a : this.order_find_all()){
                    if(a.getMember().getName().equals(orderSearch.getMemberName())){
                        orders.add(a);
                    }
                }
            }

            else{    // 두개 이상의 컬럼으로 where 조건문을 걸떄는 and 포함
                for(Order a : this.order_find_all()){
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

        //레파지토리에 findBy로 하는 방식...
        // read_search
//    public List<Customer_Order> order_find_filter(OrderSearch orderSearch) {
//
//   if(orderSearch.getMemberName())  == null && ordersearchDTO.gerorderstatus()==null){
//    List<orders> orders = new arrist<>()
//      list<member> members = memberRepositoryfindbyname(ordersearchdto.getname()){
//        for(orders orders1:orderlist){
//            orders.add((orders1))
//        }
//    }
//    }


    }
