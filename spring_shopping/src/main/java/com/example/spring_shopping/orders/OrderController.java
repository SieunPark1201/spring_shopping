package com.example.spring_shopping.orders;


import com.example.spring_shopping.items.ItemService;
import com.example.spring_shopping.member.MemberService;
import com.example.spring_shopping.orderDetail.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    MemberService memberService;  // 서비스에서 추가 기능이 없는 경우 레파지토리를 오토와이어드 해도 됨
    @Autowired
    ItemService itemService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("order")
    public String orderNew(Model model) {
        model.addAttribute("items", itemService.itemsFindAll());
        model.addAttribute("members", memberService.memberFindAll());

        return "order/orderForm";
    }

    @PostMapping("order")
    public String orderRegister(OrderDto order) throws Exception {

        // Customer_Order저장 → Order_Item 저장
        orderService.createOrder(order);
        return "redirect:/orders";
    }

    // 주문 List 화면 호출
    @GetMapping("orders")  // @ModelAttribute("맵핑 이름")할 경우 이름을 다르게 해도 됨 명시적으로 OrderSearchDto와 맵핑을 할 수 있다.
    public String orderList(Model model, OrderSearch orderSearch) {
        List<Orders> orderList = orderService.ordersSearch(orderSearch);
        model.addAttribute("orders", orderList);
        return "order/orderList";
    }

    // 더 나은 코드? 불필요한 DB조회 횟수 최소화 -> 트래픽 많을 땐
    // 주문 cancel
    @PostMapping("orders/{id}/cancel")
    public String orderCancel(@PathVariable("id") Long myid) throws Exception {
        orderService.orderUpdate(myid);
        return "redirect:/orders";
    }

    //orderDetail
    @GetMapping("orderitems")
    public String orderdetail(@RequestParam(value = "id") Long myid, Model model) {
        model.addAttribute("order_items", orderDetailRepository.findByOrders(myid));
        return "order/orderDetail";
    }


    // 주문 List 화면 호출
    @GetMapping("/orders/member")
    public String orderListmem(Model model, @RequestParam(value = "id") Long myid, OrderSearch orderSearch) {
        List<Orders> orderList = orderRepository.findByMemberId(myid);
        model.addAttribute("orders", orderList);
        return "order/orderList";
    }
}