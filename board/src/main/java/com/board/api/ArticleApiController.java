package com.board.api;

import com.board.dto.ArticleForm;
import com.board.entity.Article;
import com.board.repository.ArticleRepository;
import com.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor //3)
@Slf4j
public class ArticleApiController {
    @Autowired
    ArticleRepository articleRepository;

    // 의존성 주입법
    //1)
    // @Autowired
    // ArticleService articleService;


    //2)
    // private final ArticleService articleService;
    // public ArticleController(ArticleService articleService){
    //     this.articleService = articleService;
    // }

    //3) @RequiredArgsConstructor
    // 생성자주입(DI)
    private final ArticleService articleService;

    @GetMapping("/api/articles")
    public List<Article> index(){
        List<Article> articleList = articleService.index();
        return articleList;
        //localhost:8088/api/articles
    }

    // @GetMapping("/api/articles")
    // public List<Article> index(){
    //     // return articleRepository.findAll();
    //     List<Article> articleList = articleRepository.findAll();
    //     return articleList;
    //     //localhost:8088/api/articles
    // }


    // 상세 조회
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
        //localhost:8088/api/articles/1
    }

    // @GetMapping("/api/articles/{id}")
    // public Article show(@PathVariable Long id){
    //     Article article = articleRepository.findById(id).orElse(null);
    //     return article;
    //     //localhost:8088/api/articles/1
    // }



    @PostMapping("/api/articles")
    public ResponseEntity create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);

        if(created != null){
            return ResponseEntity.status(HttpStatus.OK).body(created);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //localhost:8088/api/articles
    }

    // @PostMapping("/api/articles")
    // public ResponseEntity create(@RequestBody ArticleForm dto){
    //     Article article = dto.toEntity(); // entity로 변환
    //
    //     // return articleRepository.save(article);
    //     Article saved = articleRepository.save(article);
    //
    //     return ResponseEntity.status(HttpStatus.OK).body(saved);
    //            // 200신호 보냄
    //
    //     //localhost:8088/api/articles
    // }


    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        log.info("id:{}",id);
        Article updated = articleService.update(id, dto);

        if(updated != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // @PatchMapping("/api/articles/{id}")
    // public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
    //     Article article = dto.toEntity(); // 수정데이터 보관
    //     log.info("id: {}, article: {}", id, article.toString());
    //     Article target = articleRepository.findById(id).orElse(null);
    //     log.info("id: {}, target: {}", id, target.toString());
    //
    //     if(target == null || !id.equals(target.getId())){
    //         ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //         //ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    //     }
    //     target.patch(article); // 엔티티 데이터 수정완료
    //     Article updated = articleRepository.save(target);
    //     // target을 article로 저장하면 post 요청이 된다(새글로 등록)
    //
    //     return ResponseEntity.status(HttpStatus.OK).body(updated);
    //     // 상태 코드와 응답본문을 클라이언트에게 전달
    // }


    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        log.info("deleted: {}", deleted);
        if(deleted != null){
            return ResponseEntity.status(HttpStatus.OK).body(deleted); //deleted를 넣은 이유는 삭제된 데이터를 보여주기 위해서 넣음
            // return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //DELETE
    // @DeleteMapping("/api/articles/{id}")
    // public ResponseEntity<String> update(@PathVariable Long id){
    //     Article target = articleRepository.findById(id).orElse(null);
    //     if(target == null){
    //         ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //     }
    //     articleRepository.delete(target);
    //
    //     return ResponseEntity.status(HttpStatus.OK).build();
    //     // return ResponseEntity.status(HttpStatus.OK).body("삭제완료");
    //     // 200 OK응답신호를 생성하고 본문을 갖지 않는 경우 build만 붙여준다.
    //
    //     // body에 보낼게 없어서 build붙임
    //     // return ResponseEntity.status(HttpStatus.OK).header("X-MyHeader","welcome").build();
    // }

}
