package com.example.spring_shopping.orders;


import com.example.spring_shopping.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByMember(Member member);
    List<Orders> findByMemberAndStatus(Member member, OrderStatus orderStatus);
    List<Orders> findByMemberId(Long id);}