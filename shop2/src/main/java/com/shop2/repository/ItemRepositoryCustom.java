package com.shop2.repository;

import com.shop2.dto.ItemSearchDto;
import com.shop2.dto.MainItemDto;
import com.shop2.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    // 상품 조회 조건을 담고 있는 itemSearchDto 객체와
    // 페이징 정보를 담고 있는 pageable 객체를 파라미터로 받는 getMainItemPage 메소드를 정의
    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}
