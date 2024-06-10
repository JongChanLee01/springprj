package com.sbb.service;


import com.sbb.entity.Question;
import com.sbb.entity.SiteUser;
import com.sbb.exception.DataNotFoundException;
import com.sbb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        // https://mangkyu.tistory.com/70
        // Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로,
        // 참조하더라도 NPE가 발생하지 않도록 도와준다.
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent()){
            return question.get();
        }else {
            throw new DataNotFoundException("question not found");
        }
    }

    // public void create(String subject, String content){
    //     Question question = new Question();
    //     question.setSubject(subject);
    //     question.setContent(content);
    //     question.setCreateDate(LocalDateTime.now());
    //     this.questionRepository.save(question);
    // }

    public void create(String subject, String content, SiteUser user){
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        question.setAuthor(user);
        this.questionRepository.save(question);
    }

    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));

        // Pageable pageable = PageRequest.of(page, 10);
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }

    // 수정
    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }


    // 삭제
    public void delete(Question question) {
        this.questionRepository.delete(question);
    }

}
