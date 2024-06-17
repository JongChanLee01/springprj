package com.shop2.controller;

import com.shop2.entity.Answer;
import com.shop2.entity.Member;
import com.shop2.entity.Question;
import com.shop2.form.AnswerForm;
import com.shop2.service.AnswerService;
import com.shop2.service.MemberService;
import com.shop2.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    private final MemberService memberService;

    // @PostMapping("/create/{id}")
    // public String createAnswer(Model model, @PathVariable("id") Integer id,
    //                            @RequestParam(value = "content") String content){
    //     Question question = this.questionService.getQuestion(id);
    //     this.answerService.create(question, content);
    //
    //     return String.format("redirect:/service/detail/%s", id);
    // }


    // 로그인한 사용자의 정보를 알려면 스프링 시큐리티가 제공하는 Principal 객체를 사용해야 한다.
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult,
                               Principal principal){
        Question question = this.questionService.getQuestion(id);
        Member member = this.memberService.getUser(principal.getName());

        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "question_detail";
        }
        // this.answerService.create(question, answerForm.getContent());

        // this.answerService.create(question, answerForm.getContent(), member);
        // return String.format("redirect:/question/detail/%s", id);

        Answer answer = this.answerService.create(question,
                answerForm.getContent(), member);
        return String.format("redirect:/service/detail/%s#answer_%s",
                answer.getQuestion().getId(), answer.getId());
    }

    // 답변 수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String answerModify(AnswerForm answerForm, @PathVariable("id") Integer id, Principal principal) {
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getEmail().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerForm.setContent(answer.getContent());
        return "service_center/answer/answer_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
                               @PathVariable("id") Integer id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "service_center/answer/answer_form";
        }
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getEmail().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.answerService.modify(answer, answerForm.getContent());
        // return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
        return String.format("redirect:/service/detail/%s#answer_%s",
                answer.getQuestion().getId(), answer.getId());
    }


    // 답변 삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getEmail().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.answerService.delete(answer);
        return String.format("redirect:/service/detail/%s", answer.getQuestion().getId());
    }


    // 답변 추천
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String answerVote(Principal principal, @PathVariable("id") Integer id) {
        Answer answer = this.answerService.getAnswer(id);
        Member member = this.memberService.getUser(principal.getName());
        this.answerService.vote(answer, member);
        // return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
        return String.format("redirect:/service/detail/%s#answer_%s",
                answer.getQuestion().getId(), answer.getId());
    }
}
