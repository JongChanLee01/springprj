package com.board.controller;

import com.board.api.CommentApiController;
import com.board.dto.BoardDto;
import com.board.dto.CommentDto;
import com.board.entity.Article;
import com.board.entity.BoardEntity;
import com.board.repository.ArticleRepository;
import com.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Controller
public class BoardController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentApiController commentApiController;

    @GetMapping({"/",""})
    public String index(){
        return "board/new";
    }

    @GetMapping("/boards/new")
    public String newArticleForm() {
        return "board/new";
    }

    @GetMapping("boards")
    public String index(Model model){
        List<BoardEntity> boardList= boardRepository.findAll();

        model.addAttribute("boards",boardList);
        return "board/index";
    }

    // @PostMapping("/boards/create")
    // public String createArticle(ArticleForm form) {
    //
    // Article article = form.toEntity();
    // Article saved = articleRepository.save(article);
    //
    // // 리다이렉트 적용: 생성 후, 브라우저가 해당 URL로 재요청
    // return "redirect:/boards/" + saved.getId();
    // }

    @GetMapping("/board/{id}") // 해당 URL요청을 처리 선언
    public String show(@PathVariable Long id, Model model) { // URL에서 id를 변수로 가져옴

        // 1: id로 데이터를 가져옴
        BoardEntity boardEntity = boardRepository.findById(id).orElse(null);

        // 2: 가져온 데이터를 모델에 등록
        model.addAttribute("board", boardEntity);

        // 3: 보여줄 페이지를 설정
        return "board/show";
    }

    @PostMapping("/boards/create")
    public ResponseEntity<BoardEntity> save(@RequestBody BoardDto boardDto) {
        BoardEntity boardEntity= boardDto.toEntity();

        BoardEntity saved = boardRepository.save(boardEntity);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }
}
