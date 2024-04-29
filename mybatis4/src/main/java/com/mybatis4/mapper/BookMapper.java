package com.mybatis4.mapper;

import com.mybatis4.dto.Book;
import com.mybatis4.dto.Student;
import com.mybatis4.model.Pagination;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("SELECT * FROM book WHERE id = #{id}")
    Book findOne(int id);

    // @Select("SELECT * FROM book WHERE categoryId = #{categoryId}")
    // Book findByCategoryId(int categoryId);

    @Select("""
            SELECT b.*, c.name bookCategory
            FROM book b JOIN category c ON b.categoryId = c.id
            ORDER BY b.id
            LIMIT #{firstRecordIndex}, #{sz}
            """)
    List<Book> findAll(Pagination pagination);

    @Select("SELECT COUNT(id) FROM book")
    int getCount();


    @Insert("""
        INSERT book (title, author, categoryId, price, publisher)
        VALUES (#{title}, #{author}, #{categoryId}, #{price}, #{publisher}) 
        """)
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Book book);

    @Update("""
        UPDATE book SET
          title= #{title},
          author = #{author},
          categoryId = #{categoryId},
          price = #{price},
          publisher = #{publisher}
        WHERE id = #{id} """)
    void update(Book book);

    @Delete("DELETE FROM book WHERE id = #{id}")
    void delete(int id);
}
