package com.example.spring_shopping.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ItemService{

    @Autowired
    ItemRepository itemRepository;


//    create
    public void itemCreate(ItemDto item){
        Item item1 = Item.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .build();

        itemRepository.save(item1);
    }



//    findAll
    public List<Item> itemsFindAll() {
        return itemRepository.findAll();
    }


//    findOne
    public Item itemsFindOne(Long id){
        return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


//    update
    public void itemUpdate(ItemDto item) throws Exception{
        Item item1 = itemRepository.findById(item.getId()).orElseThrow(EntityNotFoundException::new);

        if (item1 == null){
            throw new Exception();
        } else {

            item1.updateItem(item.getPrice(), item.getStockQuantity());
            itemRepository.save(item1);

        }
    }

//    delete
    public void itemDelete(Long id) {
        itemRepository.deleteById(id);
    }


}