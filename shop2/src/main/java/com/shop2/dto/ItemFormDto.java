package com.shop2.dto;

import com.shop2.constant.ItemSellStatus;
import com.shop2.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    private String itemCategory; // 상품 종류 추가

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;
    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    // DTO(Data Transfer Object) : 클라이언트와 서버 간 데이터 전송을 위해 설계된 객체
    // Entity : 데이터베이스에 저장되는 데이터 객체로, 데이터베이스와 직접적으로 연결

    // Dto -> Entity
    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }

    // Entity -> Dto
    public static ItemFormDto of(Item item){
        return modelMapper.map(item,ItemFormDto.class);
    }
}
