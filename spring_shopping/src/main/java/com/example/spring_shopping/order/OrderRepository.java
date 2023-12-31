package com.example.spring_shopping.order;

import com.example.spring_shopping.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    //findByA를 하면 A컬럼을 where조건으로 넣는 것.
    List<Order> findByMember(Member member);
    List<Order> findByMemberAndStatus(Member member, OrderStatus orderStatus);
    List<Order> findByMemberId(Long id);

}
