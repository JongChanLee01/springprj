package com.shop2.controller;

import com.shop2.dto.OrderHistDto;
import com.shop2.entity.Answer;
import com.shop2.entity.Member;
import com.shop2.entity.Question;
import com.shop2.exception.DataNotFoundException;
import com.shop2.form.AnswerForm;
import com.shop2.form.QuestionForm;
import com.shop2.repository.QuestionRepository;
import com.shop2.service.AnswerService;
import com.shop2.service.MemberService;
import com.shop2.service.QuestionService;
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
@Controller
@RequestMapping("/service")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final QuestionRepository questionRepository;
    private final MemberService memberService;

    // @GetMapping("/list")
    // public String user_service(Model model){
    //     List<Question> questionList = this.questionRepository.findAll();
    //     model.addAttribute("questionList", questionList);
    //     return "service_center/service_center";
    // }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Question> paging = this.questionService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "service_center/service_center";
    }


    // 답변 페이징 만들기
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm, @RequestParam(value = "answerPage", defaultValue = "0") int answerPage) {

        Question question = this.questionService.getQuestion(id);

        Page<Answer> answerPaging = this.answerService.getList(question, answerPage);

        model.addAttribute("question", question);
        model.addAttribute("answerPaging", answerPaging);

        log.info("answerPaging,{}",answerPaging.getContent());

        return "service_center/question/question_detail";

    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return  "service_center/question/question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "service_center/question/question_form";
        }

        Member member = this.memberService.getUser(principal.getName());

        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), member);
        return "redirect:list";
    }

    // 수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm,
                                 @PathVariable("id") Integer id,Principal principal) {
        Question question = this.questionService.getQuestion(id);

        // 질문 작성자와 로그인 한 아이디가 같지 않으면 아래 if문 실행
        if(!question.getAuthor().getEmail().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "service_center/question/question_form";
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
        if (!question.getAuthor().getEmail().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/service/detail/%s", id);
    }

    // 삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getEmail().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.questionService.delete(question);
        return "redirect:list";
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
        Member member = this.memberService.getUser(principal.getName());
        this.questionService.vote(question, member);
        return String.format("redirect:/service/detail/%s", id);
    }

}
