package com.shop2.repository;

import com.shop2.entity.Item;
import com.shop2.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

// public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
public interface ItemImgRepository extends JpaRepository<ItemImg, Long>,
        QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {
    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
}
