package com.board.service;

import com.board.dto.ArticleForm;
import com.board.entity.Article;
import com.board.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    public List<Article> index() {
        List<Article> articleList = articleRepository.findAll();
        return articleList;
    }

    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto){
        Article article = dto.toEntity();

        if(dto.getId() == article.getId()) return null;

        Article saved = articleRepository.save(article);
        return saved;
    }

    public Article update(Long id, ArticleForm dto){
        Article article = dto.toEntity();
        Article target = articleRepository.findById(id).orElse(null);
        Article updated;

        if(target == null || !id.equals(target.getId())){
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }else {
            target.patch(article);
            updated = articleRepository.save(target);
            return updated;
        }
    }

    public Article delete(Long id){
        Article target = articleRepository.findById(id).orElse(null);

        log.info("d_target: {}", target);
        if(target == null){ return null; }

        articleRepository.delete(target);
        return target;
    }

    @Transactional // transactional을 빼면 에러가 있든 없든 값은 저장됨
    public List<Article> createArticles(List<ArticleForm> dtos){
        // dto 묶음을 entity 묶음으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        
        // 위에를 반복문으로
        // List<Article> articleList = new ArrayList<>();
        // for (int i = 0; i < dtos.size(); i++){
        //     ArticleForm dto = dtos.get(i);
        //     Article entity = dto.toEntity();
        //     articleList.add(entity);
        // }

        // entity 묶음을 DB로 저장
        // stream 문법
        // articleList.stream().forEach(article -> articleRepository.save(article));
    
        // 위에를 반복문 - 단순문법
        for (int i = 0; i < articleList.size(); i++){
            Article article = articleList.get(i);
            articleRepository.save(article);
        }
        
        // 강제 예외 발생
        // articleRepository.findById(-1L).orElseThrow(()->new IllegalArgumentException("결재실패"));

        // 결과값 반환(형식상 반환)
        return articleList;
    }
}
