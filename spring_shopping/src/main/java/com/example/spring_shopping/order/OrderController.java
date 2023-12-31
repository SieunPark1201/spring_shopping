package com.example.spring_shopping.order;


import com.example.spring_shopping.items.Item;
import com.example.spring_shopping.items.ItemRepository;
import com.example.spring_shopping.items.ItemService;
import com.example.spring_shopping.member.MemberService;
import com.example.spring_shopping.orderDetail.OrderDetail;
import com.example.spring_shopping.orderDetail.OrderDetailDto;
import com.example.spring_shopping.orderDetail.OrderDetailRepository;
import com.example.spring_shopping.orderDetail.OrderDetailService;
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
    OrderDetailRepository orderItemRepository;
    @Autowired
    OrderRepository customerOrderRepository;

    @GetMapping("order")
    public String order_new(Model model) {
        model.addAttribute("items", itemService.item_findall());
        model.addAttribute("members", memberService.member_findall());

        return "order/orderForm";
    }

    @PostMapping("order")
    public String order_regist(OrderDto order) throws Exception {

        // Customer_Order저장 → Order_Item 저장
        orderService.order_save(order);
        return "redirect:/orders";
    }

    // 주문 List 화면 호출
    @GetMapping("orders")  // @ModelAttribute("맵핑 이름")할 경우 이름을 다르게 해도 됨 명시적으로 OrderSearchDto와 맵핑을 할 수 있다.
    public String orderList(Model model, OrderSearch orderSearch) {
        List<Order> orderList = orderService.order_find_search(orderSearch);
        model.addAttribute("orders", orderList);
        return "order/orderList";
    }

    // 더 나은 코드? 불필요한 DB조회 횟수 최소화 -> 트래픽 많을 땐
    // 주문 cancel
    @PostMapping("orders/{id}/cancel")
    public String orderCancel(@PathVariable("id") Long myid) throws Exception {
        orderService.order_change_status(myid);
        return "redirect:/orders";
    }

    //orderDetail
    @GetMapping("orderitems")
    public String orderdetail(@RequestParam(value = "id") Long myid, Model model) {
        model.addAttribute("order_items", orderItemRepository.findByCustomerOrderId(myid));
        return "order/orderDetail";
    }


    // 주문 List 화면 호출
    @GetMapping("/orders/member")
    public String orderListmem(Model model, @RequestParam(value = "id") Long myid, OrderSearch orderSearch) {
        List<Order> orderList = customerOrderRepository.findByMemberId(myid);
        model.addAttribute("orders", orderList);
        return "order/orderList";
    }

}