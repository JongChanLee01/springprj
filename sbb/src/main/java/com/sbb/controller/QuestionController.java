package com.sbb.controller;

import com.sbb.entity.Answer;
import com.sbb.entity.Question;
import com.sbb.entity.SiteUser;
import com.sbb.exception.DataNotFoundException;
import com.sbb.form.AnswerForm;
import com.sbb.form.QuestionForm;
import com.sbb.repository.QuestionRepository;
import com.sbb.service.AnswerService;
import com.sbb.service.QuestionService;
import com.sbb.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final QuestionService questionService;
    private final UserService userService;
    private final AnswerService answerService;

    // @GetMapping("/list")
    // //@ResponseBody //이제 템플릿을 사용하기 때문에 기존에 사용하던 @ResponseBody 애너테이션은 필요 없으므로 삭제
    // public String list(Model model){
    //     List<Question> questionList = this.questionRepository.findAll();
    //
    //     model.addAttribute("questionList", questionList);
    //
    //     return "question_list";
    // }

    // @GetMapping("/list")
    // public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
    //     Page<Question> paging = this.questionService.getList(page);
    //     model.addAttribute("paging", paging);
    //     return "question_list";
    // }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Question> paging = this.questionService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "question_list";
    }


    // @GetMapping("/detail/{id}")
    // public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
    //
    //     Question question = this.questionService.getQuestion(id);
    //     model.addAttribute("question", question);
    //     return "question_detail";
    // }

    // 답변 페이징 만들기
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm, @RequestParam(value = "answerPage", defaultValue = "0") int answerPage) {

        Question question = this.questionService.getQuestion(id);

        Page<Answer> answerPaging = this.answerService.getList(question, answerPage);

        model.addAttribute("question", question);
        model.addAttribute("answerPaging", answerPaging);

        log.info("answerPaging,{}",answerPaging);

        return "question_detail";

    }




    // @PreAuthorize("isAuthenticated()")는 로그인을 하지 않고 질문을 작성 하였을때
    // Principal객체가 null일때 오류를 방지해줄때 쓰는 어노테이션
    // principal가 null일때 작성하려고 버튼을 클릭을 하면 자동으로 로그인 페이지로 감
    // PreAuthorize를 사용하려면
    // SecurityConfig에 @EnableMethodSecurity(prePostEnabled = true) 추가를 해주어야함
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }
    
    // @PostMapping("/create")
    // public String questionCreate(@RequestParam(value = "subject") String subject,
    //                              @RequestParam(value = "content") String content){
    //     return "redirect:/question/list"; // 질문 저장 후 질문 목록으로
    // }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm,
             BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        SiteUser siteUser = this.userService.getUser(principal.getName());

        // this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
        return "redirect:/question/list";
    }


    // 수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm,
             @PathVariable("id") Integer id,Principal principal) {
        Question question = this.questionService.getQuestion(id);

        // 질문 작성자와 로그인 한 아이디가 같지 않으면 아래 if문 실행
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Question question = this.questionService.getQuestion(id);

        // 질문 작성자와 로그인 한 아이디가 같지 않으면 아래 if문 실행
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }


    // 삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.questionService.delete(question);
        return "redirect:/";
    }

    // 조회수
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            Question question1 = question.get();
            question1.setView(question1.getView()+1);
            this.questionRepository.save(question1);
            return question1;
        } else {
            throw new DataNotFoundException("question not found");
        }
    }


    // 추천
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String questionVote(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionService.vote(question, siteUser);
        return String.format("redirect:/question/detail/%s", id);
    }
}
