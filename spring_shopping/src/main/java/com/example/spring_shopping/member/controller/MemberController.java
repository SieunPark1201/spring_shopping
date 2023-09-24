package com.example.spring_shopping.member.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {


    @GetMapping("/members/new")
    public String createForm(Model model) {
//        createMemberForm화면에서 memberForm이라는 Dto 객체를 필요로 하므로,
//        dto객체를 만들어서 model을 통해 전달
//        왜 이렇게 해야하냐?
//        => 스프링에서 validation(그 값이 필수값이냐 아니냐)을 추가하게 되면 Dto에서 @NotEmpty, @NotNull를 사용할 수 있음
//        Dto에서 notnull, notempty등 validation처리를 할 수 있고, 해당 객체를 createMemberForm화면에 전달함으로써
//        화면에서 validation 체크를 할 수 있다
        model.addAttribute("memberForm", new MemberDto());
        return "members/createMemberForm";
    }


}

