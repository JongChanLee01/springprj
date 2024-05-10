package com.board.controller;

import com.board.dto.ArticleForm;
import com.board.entity.Article;
import com.board.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;
    
    @GetMapping("/articles")
    public String index(Model model){
        // 아래처럼 강제 형변환을 해주거나 레퍼지토리에서 findAll을 새로 정의를 해준다.
        // List<Article> articleEntityList = (List<Article>)articleRepository.findAll();
        List<Article> articleEntityList = articleRepository.findAll();
        
        model.addAttribute("article", articleEntityList);
        return "articles/index";
    }

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm articleForm){
        // 1. dto를 Entity로 변환
        Article article = articleForm.toEntity();

        // 2. repository에게 entity를 저장하게 한다.
        Article saved = articleRepository.save(article);
        System.out.println(articleForm.toString());

        return "";
    }
}
