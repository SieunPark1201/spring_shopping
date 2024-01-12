package com.example.spring_shopping.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MemberService{

    @Autowired
    MemberRepository memberRepository;

    // create
    public void createMember(MemberRequestDto memberRequestDto){
        Address address = new Address(memberRequestDto.getCity(), memberRequestDto.getZipcode(), memberRequestDto.getStreet());
        Member member1 = Member.builder()
                .name(memberRequestDto.getName())
                .email(memberRequestDto.getEmail())
                .password(memberRequestDto.getPassword())
                .address(address)
                .build();

        memberRepository.save(member1);
    }

    //readAll
    public List<Member> memberFindAll(){
        return memberRepository.findAll();
    }

    //readOne

    public Member memberFindOne(Long id) {
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}