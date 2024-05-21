package com.blog.api;

import com.blog.dto.ResponseDto;
import com.blog.model.Board;
import com.blog.model.Reply;
import com.blog.model.User;
import com.blog.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @Autowired
    HttpSession session;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board){
        // boardService.글쓰기(board);
        // return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
        Boolean isSession= boardService.글쓰기(board);
        if(isSession==true){
            return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
        }else{
            return new ResponseDto<Integer>(HttpStatus.OK.value(),0);
        }
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


    // 댓글올리기
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply){
        User user= (User) session.getAttribute("principal");

        boardService.댓글쓰기(user, boardId, reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    // 댓글 삭제
    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.댓글삭제(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}