package com.example.spring_shopping.member;


import com.example.spring_shopping.order.Order;
import com.example.spring_shopping.order.OrderRepository;
import com.example.spring_shopping.order.OrderStatus;
import com.example.spring_shopping.orderDetail.OrderDetail;
import com.example.spring_shopping.orderDetail.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {


    @Autowired
    MemberService memberService;
    @Autowired
    OrderRepository customerOrderRepository;
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("members/new")
    public String member_new(Model model) {
        // createMemberForm에서 memberForm이라는 Dto 객체를 필요로 하므로, dto객체를 만들어서 model을 통해 전달
        // DTO에서 NotNull, NotEmpty등 validation 처리를 할 수가 있고, 해당 객체를 createMemberForm화면에 전달함으로서
        // 화면에서 validation체크를 할 수가 있다.
        model.addAttribute("memberForm", new MemberRequestDto());
        return "members/createMemberForm";
    }


    @PostMapping("members/new")
    public String member_new_post(MemberRequestDto member) {
        memberService.member_save(member);
        return "redirect:/members";

    }

    @GetMapping("members")
    public String memberFindAll(Model model) throws SQLException {   //타서버 조회시 강제시킴

        List<Member> members = memberService.member_findall();

        List<MemberResponseDto> memberResponseDtos1 = new ArrayList<>();

        for (Member a : members) {
            //ResponseDto입력
            int total = 0;
            for (Order b : a.getCustomerOrders()) {
                if (b.getStatus() != OrderStatus.CANCELED) {
                    for (int i = 0; i < b.getOrderItems().size(); i++) {
                        total += b.getOrderItems().get(i).getCount().intValue() * b.getOrderItems().get(i).getOrderPrice();
                    }
                }
            }
            MemberResponseDto memberResponseDtos = new MemberResponseDto();
            memberResponseDtos.setId(a.getId());
            memberResponseDtos.setName(a.getName());
            memberResponseDtos.setEmail(a.getEmail());
            memberResponseDtos.setCity(a.getAddress().getCity());
            memberResponseDtos.setStreet(a.getAddress().getStreet());
            memberResponseDtos.setZipcode(a.getAddress().getZipcode());
            memberResponseDtos.setOrdercount(Long.parseLong(String.valueOf(a.getCustomerOrders().size())));
            memberResponseDtos.setTotal((long) total);
            memberResponseDtos1.add(memberResponseDtos);

        }

        model.addAttribute("members", memberResponseDtos1);

        return "members/memberList";


    }
}

