package com.blog.service;

import com.blog.model.Board;
import com.blog.model.User;
import com.blog.repository.BoardRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private HttpSession session;

    @Transactional
    public void 글쓰기(Board board){ // title, content
        User user= (User) session.getAttribute("principal");

        board.setCount(0); // 조회수 0
        board.setUser(user);

        boardRepository.save(board);
    }

    public List<Board> 글목록(){
        return boardRepository.findAll();
    }
}