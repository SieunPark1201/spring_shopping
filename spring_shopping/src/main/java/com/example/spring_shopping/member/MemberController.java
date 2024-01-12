package com.example.spring_shopping.member;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController{

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("members/new")
    public String memberRegister(Model model){
        model.addAttribute("memberForm", new MemberRequestDto());
        return "members/createMemberForm";
    }


    @PostMapping("members/new")
    public String memberRegisterPost(MemberRequestDto memberRequestDto) {
        memberService.createMember(memberRequestDto);
        return "redirect:/members";

    }





    @GetMapping("members")
    public String memberFindAll(Model model) throws SQLException {

        List<Member> members = memberService.memberFindAll();
        List<MemberResponseDto> memberResponseDtos1 = new ArrayList<>();

        for(Member a : members) {
            int total = 0;

            MemberResponseDto memberResponseDtos = new MemberResponseDto();
            memberResponseDtos.setId(a.getId());
            memberResponseDtos.setName(a.getName());
            memberResponseDtos.setEmail(a.getEmail());
            memberResponseDtos.setCity(a.getAddress().getCity());
            memberResponseDtos.setStreet(a.getAddress().getStreet());
            memberResponseDtos.setZipcode(a.getAddress().getZipcode());
            memberResponseDtos.setOrderCount(Long.parseLong(String.valueOf(a.getMemberOrders().size())));
            memberResponseDtos.setTotal((long) total);
            memberResponseDtos1.add(memberResponseDtos);

        }

        model.addAttribute("members", memberResponseDtos1);

        return "members/memberList";

    }



}
