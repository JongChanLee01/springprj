package com.blog.service;

import com.blog.dto.ReplySaveRequestDto;
import com.blog.model.Board;
import com.blog.model.Reply;
import com.blog.model.User;
import com.blog.repository.BoardRepository;
import com.blog.repository.ReplyRepository;
import com.blog.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private HttpSession session;

    // @Transactional
    // public void 글쓰기(Board board){ // title, content
    //     User user= (User) session.getAttribute("principal");
    //
    //     board.setCount(0); // 조회수 0
    //     board.setUser(user);
    //
    //     boardRepository.save(board);
    // }

    // 글쓰기 세션 처리
    // @Transactional
    // public Boolean 글쓰기(Board board){ // title, content
    //     // log.info("session 1: " + session.getAttribute("principal"));
    //     if(session.getAttribute("principal") != null){
    //         User user= (User) session.getAttribute("principal");
    //         board.setCount(0);
    //         board.setUser(user);
    //         boardRepository.save(board);
    //         return true;
    //     }else{
    //         return false;
    //     }
    // }

    @Transactional
    public Boolean 글쓰기(Board board, String user){ // title, content
        User user1= userRepository.findByUsername(user).orElse(null);

        board.setCount(0);
        board.setUser(user1);
        boardRepository.save(board);
        return true;
    }


    // public List<Board> 글목록(){
    //     return boardRepository.findAll();
    // }

    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public Board 글상세보기(int id) {
        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
        });
        board.setCount(board.getCount()+1);
        // boardRepository.save(board); // Transactional을 걸면 자동으로 save가 됨
        // Transctional을 안할거면 위에를 써야함
        return board;
    }

    @Transactional
    public void 글삭제하기(int id) {
        System.out.println("글 삭제하기 : " + id);
        boardRepository.deleteById(id);
        // void형임 optional이 아니다.
    }

    @Transactional
    public void 글수정하기(int id, Board requestboard) {
        // 영속화 시킨다.
        Board board=boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
        }); // 영속화 완료
        System.out.println("영속화후: " + board);

        board.setTitle(requestboard.getTitle());
        board.setContent(requestboard.getContent());

        System.out.println("영속화후2: " + board);
        // boardRepository.save(board);
        // 해당함수 종료시 (서비스가 종료될때) 트랜잭션이 종료된다. 이때 더티체킹-자동업데이트가된다.
        // db flush된다. 즉 commit이 된다.
    }

    // 댓글 쓰기
    // public void 댓글쓰기(User user, int boardId, Reply requestReply) {
    //     Board board = boardRepository.findById(boardId).orElseThrow(()->{
    //         return new IllegalArgumentException("댓글쓰기 실패: 게시글 아이디를 찾을 수 없습니다.");
    //     }); // 영속화 완료
    //
    //     requestReply.setUser(user);
    //     requestReply.setBoard(board);
    //
    //     replyRepository.save(requestReply);
    // }

    // DTO로 댓글 쓰기
    @Autowired
    UserRepository userRepository;
    
    @Transactional
    // public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
    public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto, @AuthenticationPrincipal UserDetails user) {
        Board board=boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
            return new IllegalArgumentException("댓글쓰기 실패: 게시글아이디를 찾을 수 없습니다.");
        });
        // User user=userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
        //     return new IllegalArgumentException("댓글쓰기 실패: 유저아이디 찾을 수 없습니다.");
        // });


        // User user = (User) session.getAttribute("principal");

        // SecurityContext securityContext = SecurityContextHolder.getContext();
        // Authentication authentication = securityContext.getAuthentication();
        // UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // User user=userRepository.findByUsername(userDetails.getUsername()).orElseThrow(()->{
        //     return new IllegalArgumentException("댓글쓰기 실패: 유저아이디 찾을 수 없습니다.");
        // });

        User user1=userRepository.findByUsername(user.getUsername()).orElseThrow(()->{
            return new IllegalArgumentException("댓글쓰기 실패: 유저아이디 찾을 수 없습니다.");
        });

        //dto -> entity로 변환하기

        // 방법1 -reply에 update메소드 추가하기
        Reply reply= new Reply();
        // reply.update(user,board, replySaveRequestDto.getContent());
        reply.update(user1,board, replySaveRequestDto.getContent());

        // 방법2-builder() 패턴사용하기
        // Reply reply= Reply.builder().user(user).board(board)
        //         .content(replySaveRequestDto.getContent()).id(1).build();

        // reply.update에서 업데이트로 해서 save를 따로 해주어야함
        replyRepository.save(reply);
    }

    public void 댓글삭제(int replyId) {
        replyRepository.deleteById(replyId);
    }

    @Transactional
    public void 댓글수정(int replyId, Reply reply) {
        Reply reply1=replyRepository.findById(replyId).orElse(null);
        reply1.setContent(reply.getContent());
        // dirty checking 
        // replyRepository.save(reply1); 은 Transactional을 사용하여 자동으로 저장됨
    }
}