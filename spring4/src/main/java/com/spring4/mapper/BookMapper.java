package com.spring4.mapper;

import java.util.List;

import com.spring4.dto.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
// interface는 mapping 파일에 기재된 SQL구문을 호출하기 위한 인터페이스
public interface BookMapper {
    @Select("""
            SELECT b.*, c.name categoryName
            FROM book b JOIN category c ON b.categoryId = c.id
            """)
    // 아래가 리턴값
    List<Book> findAll();
}
