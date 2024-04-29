package com.mybatis4.service;

import com.mybatis4.dto.Book;
import com.mybatis4.mapper.BookMapper;
import com.mybatis4.model.BookEdit;
import com.mybatis4.model.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookMapper bookMapper;

    ModelMapper modelMapper = new ModelMapper();

    public BookEdit findOne(int id) {
        Book bookDto = bookMapper.findOne(id);
        return toEditModel(bookDto);
    }

    // public Book findByCategoryId(int categoryId) {
    //     return bookMapper.findByCategoryId(categoryId);
    // }

    public List<Book> findAll(Pagination pagination) {
        pagination.setRecordCount(bookMapper.getCount());
        return bookMapper.findAll(pagination);
    }


    public void insert(BookEdit bookEdit,
                       BindingResult bindingResult, Pagination pagination) throws Exception {
        if (hasErrors(bookEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Book book = toDto(bookEdit);
        bookMapper.insert(book);

        int lastPage = (int)Math.ceil((double)bookMapper.getCount() / pagination.getSz());
        pagination.setPg(lastPage);
    }

    public void update(BookEdit bookEdit,
                       BindingResult bindingResult) throws Exception {
        if (hasErrors(bookEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Book book = toDto(bookEdit);
        bookMapper.update(book);
    }

    public void delete(int id) {
        bookMapper.delete(id);
    }
    public Book toDto(BookEdit bookEdit) {
        return modelMapper.map(bookEdit, Book.class);
    }
    public BookEdit toEditModel(Book bookDto) {
        return modelMapper.map(bookDto, BookEdit.class);
    }
    public boolean hasErrors(BookEdit bookEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return true;
        // Book book2 = findByCategoryId(bookEdit.getCategoryId());
        // if (book2 != null && book2.getId() != bookEdit.getId()) {
        //     bindingResult.rejectValue("categoryId", null, "카테고리 id가 중복됩니다.");
        //     return true;
        // }
        return false;
    }
}
