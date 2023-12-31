package com.example.spring_shopping.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByName(String itemName);

}