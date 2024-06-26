package com.board.controller;

import com.board.dto.ArticleForm;
import com.board.dto.CommentDto;
import com.board.entity.Article;
import com.board.repository.ArticleRepository;
import com.board.service.ArticleService;
import com.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Slf4j
@Controller
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CommentService commentService;

    // @GetMapping("/articles")
    // public String index(Model model){
    //     // 아래처럼 강제 형변환을 해주거나 레퍼지토리에서 findAll을 새로 정의를 해준다.
    //     // List<Article> articleEntityList = (List<Article>)articleRepository.findAll();
    //     List<Article> articleEntityList = articleRepository.findAll();
    //
    //     model.addAttribute("article", articleEntityList);
    //     return "articles/index";
    // }

    @GetMapping("/articles")
    public String index(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                        @RequestParam(value = "perPageNum", defaultValue = "5") int perPageNum,
                        Model model) {

        int totalCount= (int) articleRepository.findAll().stream().count();
        int totalPages = (int) Math.ceil((double) totalCount / perPageNum);

        int start = (pageNum - 1) * perPageNum;
        int end=start+5;

        start= start< 0 ? start=0 : (pageNum - 1) * perPageNum ;
        end= end >=totalCount ? end=totalCount : start+5;

        boolean preStatus;
        boolean nextStatus;

        int[] pageNums = new int[totalPages];

        for(int i=0;i<totalPages;i++){
            pageNums[i]=(i+1);
        }

        int preVious=pageNum-1;
        int next=pageNum+1;

        if(start<=0){
            preStatus=false;
        }else{
            preStatus=true;
        }

        if(end>=totalCount){
            nextStatus=false;
        }else{
            nextStatus=true;
        }

        // List<Article> articleEntityList =articleRepository.findAll().subList(start,end);
        Sort descendingSort = Sort.by(Sort.Direction.DESC,"id");
        List<Article> articleEntityList = articleRepository.findAll(descendingSort).subList(start,end);

        model.addAttribute("article", articleEntityList);

        model.addAttribute("pageNums", pageNums);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("preVious", preVious);
        model.addAttribute("next", next);
        model.addAttribute("preStatus",preStatus);
        model.addAttribute("nextStatus",nextStatus);
        model.addAttribute("pageNum",pageNum);

        log.info("pre {}, next {}", preStatus, nextStatus);
        log.info("start {}, end {}, totalCount {}", start, end, totalCount);

        return "articles/index"; // articles/index.mustache
    }


    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm articleForm){
        // 1. dto를 Entity로 변환
        Article article = articleForm.toEntity();

        // 2. repository에게 entity를 DB에 저장하게 한다.
        Article saved = articleRepository.save(article);
        System.out.println(articleForm.toString());

        return "redirect:/articles";
        //return "redirect/articles/"+ saved.getId();
    }


    // 게시글 상세보기
    @GetMapping("/articles/{id}")
    // @PathVariable 경로 변수를 표시하기 위한 메서드의 매개변수에 사용
    public String show(@PathVariable long id, Model model){
        // id로 데이터를 가져온다
        // Article articleEntity = articleRepository.findById(id).orElse(null);
        Article articleEntity = articleRepository.findById(id).orElseThrow(()-> {
                    return new IllegalArgumentException("해당 유저는 없습니다. id:"+id);
                }
        );

        List<CommentDto> commentsDtos = commentService.comments(id);

        // 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentsDtos);

        //보여줄 페이지를 설정

        return "articles/show";
    }

    @GetMapping("/articles/{id}/edit")
    // @PathVariable 경로 변수를 표시하기 위한 메서드의 매개변수에 사용
    public String edit(@PathVariable long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // Optional<Article> articleEntity = articleRepository.findById(id);
        model.addAttribute("article", articleEntity);

        // 수정 폼으로 데이터 전송
        return "articles/edit";
    }

    //수정
    @PostMapping("/articles/update")
    public String update(ArticleForm articleForm){
        log.info("articleForm:"+articleForm.toString());
        // dto를 Entity로 변환
        Article articleEntity = articleForm.toEntity();

        // DB에 기존 데이터를 가져온다.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // repository에게 entity를 DB에 저장하게 한다.
        if(target != null)
            articleRepository.save(articleEntity);

        // return "redirect/articles/";
        return "redirect:/articles/" + articleEntity.getId();
    }


    // 삭제
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes msg){
        Article target = articleRepository.findById(id).orElse(null);

        if(target != null) {
            articleRepository.delete(target);
            msg.addFlashAttribute("msg","삭제가 완료되었습니다."); // 휘발성데이터

            log.info("msg:"+msg.getFlashAttributes().toString());
        }
        return "redirect:/articles";
    }
}
