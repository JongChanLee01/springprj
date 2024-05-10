package com.board.api;

import com.board.dto.ArticleForm;
import com.board.entity.Article;
import com.board.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleApiController {
    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/api/articles")
    public List<Article> index(){
        // return articleRepository.findAll();
        List<Article> articleList = articleRepository.findAll();
        return articleList;
        //localhost:8088/api/articles
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        Article article = articleRepository.findById(id).orElse(null);
        return article;
        //localhost:8088/api/articles/1
    }

    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity(); // entity로 변환

        return articleRepository.save(article);
        //localhost:8088/api/articles
    }
}
