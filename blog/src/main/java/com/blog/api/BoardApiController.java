package com.blog.api;

import com.blog.dto.ReplySaveRequestDto;
import com.blog.dto.ResponseDto;
import com.blog.model.Board;
import com.blog.model.Reply;
import com.blog.model.User;
import com.blog.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @Autowired
    HttpSession session;

    // @PostMapping("/api/board")
    // public ResponseDto<Integer> save(@RequestBody Board board){
    //     // boardService.글쓰기(board);
    //     // return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    //     Boolean isSession= boardService.글쓰기(board);
    //     if(isSession==true){
    //         return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    //     }else{
    //         return new ResponseDto<Integer>(HttpStatus.OK.value(),0);
    //     }
    // }
    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board){

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // log.info("userDetails:"+userDetails.getUsername());
        log.info("securityContext:"+securityContext);
        log.info("authentication:"+authentication);
        log.info("userDetails:"+userDetails);

        boardService.글쓰기(board,userDetails.getUsername());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board ){
        boardService.글수정하기(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }


    // 댓글 쓰기
    // @PostMapping("/api/board/{boardId}/reply")
    // public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply){
    //     User user= (User) session.getAttribute("principal");
    //
    //     boardService.댓글쓰기(user, boardId, reply);
    //     return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    // }

    // DTO로 댓글쓰기
    // 원래는 데이터를 받을 때 컨트롤러에서 dto를 만드는것이 좋다.
    // dto를 사용하지 않는 이유는 규모가 작기 때문이다.
    @PostMapping("/api/board/{boardId}/reply")
    // public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto,@AuthenticationPrincipal UserDetails user){
        // boardService.댓글쓰기(replySaveRequestDto);
        boardService.댓글쓰기(replySaveRequestDto, user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    // 댓글 삭제
    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.댓글삭제(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 댓글 수정
    @PutMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyUpdate(@PathVariable int replyId, @RequestBody Reply reply){
        boardService.댓글수정(replyId, reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}